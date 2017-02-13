package com.example.newuser1.myapplication.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newuser1.myapplication.R;
import com.example.newuser1.myapplication.dependency_injection.Injector;
import com.example.newuser1.myapplication.models.Consts;
import com.example.newuser1.myapplication.models.EpisodeContent;
import com.example.newuser1.myapplication.models.ImageObj;
import com.example.newuser1.myapplication.services.SharedPrefService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class EpisodeActivity extends AppCompatActivity
{
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.star)
    ImageView star;

    private EpisodeContent episodeContent;

    @OnClick(R.id.star)
    void onClick()
    {
        Boolean isFavRightNow = sharedPrefService.isEpisodeFavourite(episodeContent.getUid());

        sharedPrefService.saveEpisodeFavouritePref(episodeContent.getUid(), !isFavRightNow);

        if(isFavRightNow)
        {
            Glide.with(EpisodeActivity.this)
                    .load(R.drawable.star_gray)
                    .into(star);

            Toast.makeText(EpisodeActivity.this, EpisodeActivity.this.getString(R.string.episode_unfavourited), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Glide.with(EpisodeActivity.this)
                    .load(R.drawable.star_gold)
                    .into(star);

            Toast.makeText(EpisodeActivity.this, EpisodeActivity.this.getString(R.string.episode_favourited), Toast.LENGTH_SHORT).show();
        }

    }

    @Inject
    SharedPrefService sharedPrefService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

        ButterKnife.bind(this);

        Injector.getApplicationComponent().inject(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            if(extras.containsKey(Consts.EPISODE))
            {
                episodeContent = (EpisodeContent) extras.get(Consts.EPISODE);

                ImageObj imageObj = episodeContent.getImageObj();
                if(imageObj != null && !TextUtils.isEmpty(imageObj.getUrl()))
                {
                    Glide.with(this)
                            .load(episodeContent.getImageObj().getUrl())
                            .centerCrop()
                            .error(R.drawable.error)
                            .placeholder(R.drawable.loading)
                            .into(image);
                }
                else
                {
                    Glide.with(this)
                            .load(R.drawable.placeholder)
                            .centerCrop()
                            .into(image);
                }

                title.setText(episodeContent.getTitle());

            }

            if(sharedPrefService.isEpisodeFavourite(episodeContent.getUid()))
            {
                Glide.with(this)
                        .load(R.drawable.star_gold)
                        .into(star);
            }
            else
            {
                Glide.with(this)
                        .load(R.drawable.star_gray)
                        .into(star);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
