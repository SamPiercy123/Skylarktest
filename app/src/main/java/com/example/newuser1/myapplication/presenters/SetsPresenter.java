package com.example.newuser1.myapplication.presenters;

import com.example.newuser1.myapplication.models.Sets;
import com.example.newuser1.myapplication.services.SetsDLInterface;

import rx.Observer;

/**
 * Created by sam.piercy on 05/02/2017.
 */
public class SetsPresenter extends BasePresenter implements Observer<Sets>
{
    private SetsDLInterface setsDLInterface;

    public SetsPresenter(SetsDLInterface setsDLInterface)
    {
        this.setsDLInterface = setsDLInterface;
    }


    @Override
    public void onCompleted()
    {
        setsDLInterface.onSetsDLCompleted();
    }

    @Override
    public void onError(Throwable e)
    {
        setsDLInterface.onSetsDLError(e);
    }

    @Override
    public void onNext(Sets sets)
    {
        setsDLInterface.onNext(sets);

    }

    public void fetchSets(String ext)
    {
        unsubscribeAll();
        subscribe(setsDLInterface.getSets(ext) , SetsPresenter.this);
    }
}
