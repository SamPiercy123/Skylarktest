package com.example.newuser1.myapplication.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.newuser1.myapplication.adapters.FrontPageAdapter;

import com.example.newuser1.myapplication.adapters.view_holders.EpisodeViewHolder;
import com.example.newuser1.myapplication.dependency_injection.Injector;
import com.example.newuser1.myapplication.models.Consts;
import com.example.newuser1.myapplication.models.EpisodeContent;
import com.example.newuser1.myapplication.models.ImageObj;
import com.example.newuser1.myapplication.models.RealmSetItems;
import com.example.newuser1.myapplication.models.SetItem;
import com.example.newuser1.myapplication.models.Sets;
import com.example.newuser1.myapplication.presenters.EpisodeContentPresenter;
import com.example.newuser1.myapplication.presenters.ImageObjPresenter;
import com.example.newuser1.myapplication.presenters.SetsPresenter;
import com.example.newuser1.myapplication.services.EpisodeContentDLInterface;
import com.example.newuser1.myapplication.services.ImageObjDLInterface;
import com.example.newuser1.myapplication.services.Listeners;
import com.example.newuser1.myapplication.services.RealmService;
import com.example.newuser1.myapplication.services.RestService;
import com.example.newuser1.myapplication.R;


import com.example.newuser1.myapplication.services.SetsDLInterface;
import com.example.newuser1.myapplication.services.SharedPrefService;
import com.example.newuser1.myapplication.services.error_handling.RetrofitException;

import org.xml.sax.ErrorHandler;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by sam.piercy on 11/02/2017.
 * Sample
 */
public class MainActivity extends AppCompatActivity implements SetsDLInterface, EpisodeContentDLInterface, ImageObjDLInterface
{

    ProgressDialog progDog;
    ArrayList<SetItem> setItems;
    int itemsPassed;
    boolean imageDLing;

    private SetsPresenter setsPresenter;
    private EpisodeContentPresenter episodeContentPresenter;
    private ImageObjPresenter imageObjPresenter;

    @Inject
    RestService restService;

    @Inject
    RealmService realmService;

    @Inject
    SharedPrefService sharedPrefService;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        Injector.getApplicationComponent()
                .inject(this);



        if(savedInstanceState != null && savedInstanceState.containsKey(Consts.SETITEMSSAVE))
        {
            setItems = savedInstanceState.getParcelableArrayList(Consts.SETITEMSSAVE);
            setAdapter();
        }

        else
        {
            RealmSetItems rsis = realmService.findFirst(RealmSetItems.class);
            if(rsis != null)
            {
                setItems = new ArrayList<>();
                setItems.addAll(rsis.getSetItems());
                setAdapter();
            }
            else
            {
                setsPresenter = new SetsPresenter(MainActivity.this);

                episodeContentPresenter = new EpisodeContentPresenter(MainActivity.this);

                imageObjPresenter = new ImageObjPresenter(MainActivity.this);

                setsPresenter.fetchSets(Consts.SETS_URL);
            }

        }




    }


    @Override
    protected void onPause()
    {

        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        if(progDog != null && progDog.isShowing())
        {
            progDog.dismiss();
        }
        realmService.close();

        super.onDestroy();
    }


    @Override
    public void onSetsDLCompleted()
    {

    }

    @Override
    public void onSetsDLError(Throwable e) {
        handleDLError(e);
    }

    @Override
    public void onNext(Sets sets)
    {

        setItems = (ArrayList<SetItem>) sets.getSetheads().get(0).getItems();
        itemsPassed = 0;
        episodeContentLoadTrain();


    }

    @Override
    public Observable<Sets> getSets(String setExt)
    {
        showProgressDialog();

        return restService.getSets(setExt, Consts.HOME);
    }

    @Override
    public void onEpisodeContentDLCompleted()
    {
        if(!imageDLing) //If image is downloading we want to download it before we move on.
        {
            itemsPassed++;
            episodeContentLoadTrain();
        }
    }

    @Override
    public void onEpisodeContentDLError(Throwable e)
    {
        handleDLError(e);
    }

    @Override
    public void onNext(EpisodeContent episodeContent)
    {
        setItems.get(itemsPassed).setEpisodeContent(episodeContent);

        if (episodeContent.getImageUrls() != null && !episodeContent.getImageUrls().isEmpty())
        {
            imageObjPresenter.fetchImageObj(episodeContent.getImageUrls().get(0).getValue());
            imageDLing = true;
        }



    }

    @Override
    public Observable<EpisodeContent> getEpisodeContent(String setExt)
    {

        return restService.getEpisode(setExt);
    }

    private void episodeContentLoadTrain()
    {
        boolean found = false;
        while(!found && setItems.size() > itemsPassed)
        {
            if(Consts.EPISODE.equals(setItems.get(itemsPassed).getContentType()))
            {
                found = true;
                episodeContentPresenter.fetchEpisodeContent(setItems.get(itemsPassed).getContentUrl());
            }
            else
            {
                itemsPassed++;
            }
        }
        if (setItems.size() == itemsPassed)
        {
            trainHasReachedDestination();
        }
    }

    private void setAdapter()
    {
        if(progDog != null)
        progDog.dismiss();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        FrontPageAdapter adapter = new FrontPageAdapter(setItems);
        adapter.setOnItemClickListener(new Listeners.OnItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Intent intent = new Intent(MainActivity.this , EpisodeActivity.class);
                intent.putExtra(Consts.EPISODE, (EpisodeContent) item);
                startActivityForResult(intent, Consts.EPISODE_REQUEST);
                Log.d("Sam", ((EpisodeContent) item).getTitle());

            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onImageObjDLCompleted()
    {
        imageDLing = false;
        itemsPassed++;
        episodeContentLoadTrain();
    }

    @Override
    public void onImageObjDLError(Throwable e)
    {
        handleDLError(e);
    }

    @Override
    public void onNext(ImageObj imageObj)
    {
        setItems.get(itemsPassed).getEpisodeContent().setimageObj(imageObj);
    }

    @Override
    public Observable<ImageObj> getImageObj(String setExt)
    {
        return restService.getImageObj(setExt);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {
        if(setItems != null)
        {
            outState.putParcelableArrayList(Consts.SETITEMSSAVE , setItems);
        }
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(setItems != null)
        {
            outState.putParcelableArrayList(Consts.SETITEMSSAVE , setItems);
        }
        super.onSaveInstanceState(outState);
    }

    private void trainHasReachedDestination()
    {
        realmService.addSetItems(setItems);
        setAdapter();
    }

    private void showProgressDialog()
    {
        progDog = new ProgressDialog(this);
        progDog.setTitle(getString(R.string.contacting_site));
        progDog.setCancelable(false);
        progDog.show();
    }

    private void handleDLError(Throwable e)
    {
        RetrofitException error = (RetrofitException) e;
        try {
            restService = error.getErrorBodyAs(RestService.class);
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
    }

    public SharedPrefService getSharedPrefService()
    {
        return sharedPrefService;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Consts.EPISODE_REQUEST)
        {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}

