package com.example.newuser1.myapplication.presenters;


import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sam.piercy on 11/02/2017.
 */
public class BasePresenter implements Presenter
{
    private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate()
    {

    }

    @Override
    public void onDestroy()
    {
        unsubscribeAll();
    }

    protected void unsubscribeAll()
    {
        if (compositeSubscription != null)
        {
            compositeSubscription.unsubscribe();
            compositeSubscription.clear();
        }
    }

    @Override
    public void onPause()
    {

    }

    @Override
    public void onResume()
    {
        configureSubscription();
    }

    private CompositeSubscription configureSubscription()
    {
        if (compositeSubscription == null || compositeSubscription.isUnsubscribed())
        {
            compositeSubscription = new CompositeSubscription();
        }
        return compositeSubscription;

    }

    protected <T> void subscribe (Observable<T> observable, Observer<T> observer)
    {

        Subscription subscription = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);

        configureSubscription().add(subscription);

    }

}
