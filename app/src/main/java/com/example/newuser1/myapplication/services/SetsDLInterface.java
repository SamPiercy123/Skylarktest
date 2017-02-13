package com.example.newuser1.myapplication.services;

import com.example.newuser1.myapplication.models.Sets;

import rx.Observable;

/**
 * Created by sam.piercy on 11/02/2017.
 */
public interface SetsDLInterface
{
    void onSetsDLCompleted();

    void onSetsDLError(Throwable e);

    void onNext(Sets sets);

    Observable<Sets> getSets(String setExt);
}
