package com.example.newuser1.myapplication.application;

import android.app.Application;


import com.example.newuser1.myapplication.dependency_injection.Injector;
import com.example.newuser1.myapplication.models.SetItem;
import com.example.newuser1.myapplication.services.RealmService;
import com.example.newuser1.myapplication.services.SharedPrefService;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Sam Piercy on 11/02/2017.
 */
public class RestTestApplication extends Application
{


    @Inject
    SharedPrefService sharedPrefService;
    @Inject
    RealmService realmService;

    ArrayList<SetItem> setItems;

    @Override
    public void onCreate()
    {
        super.onCreate();
        resolveDependencies();
    }

    private void resolveDependencies()
    {
        Injector.initializeApplicationComponent(this);
        Injector.getApplicationComponent().inject(this);

        initRealm();

    }

    protected void initRealm()
    {
        realmService.setup();
    }

    public ArrayList<SetItem> getSetItems() {
        return setItems;
    }

    public void setSetItems(ArrayList<SetItem> setItems) {
        this.setItems = setItems;
    }


}
