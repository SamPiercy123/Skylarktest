package com.example.newuser1.myapplication.presenters;

import com.example.newuser1.myapplication.models.EpisodeContent;
import com.example.newuser1.myapplication.models.Sets;
import com.example.newuser1.myapplication.services.EpisodeContentDLInterface;
import com.example.newuser1.myapplication.services.SetsDLInterface;

import rx.Observer;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class EpisodeContentPresenter extends BasePresenter implements Observer<EpisodeContent> {

    private EpisodeContentDLInterface epContDlInt;

    public EpisodeContentPresenter(EpisodeContentDLInterface epContDlInt)
    {
        this.epContDlInt = epContDlInt;
    }

    @Override
    public void onCompleted()
    {
        epContDlInt.onEpisodeContentDLCompleted();
    }

    @Override
    public void onError(Throwable e)
    {
        epContDlInt.onEpisodeContentDLError(e);
    }

    @Override
    public void onNext(EpisodeContent epCont)
    {
        epContDlInt.onNext(epCont);

    }

    public void fetchEpisodeContent(String ext)
    {
        unsubscribeAll();
        ext = ext.substring(1); //remove the first '/' to work with retrofit2.
        subscribe(epContDlInt.getEpisodeContent(ext) , EpisodeContentPresenter.this);
    }
}
