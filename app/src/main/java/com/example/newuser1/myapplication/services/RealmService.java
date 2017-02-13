package com.example.newuser1.myapplication.services;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

import android.content.Context;

import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.dependency_injection.Injector;
import com.example.newuser1.myapplication.models.RealmSetItems;
import com.example.newuser1.myapplication.models.SetItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class RealmService {

    @Inject
    Context mContext;

    RealmConfiguration realmConfiguration;

    public RealmService()
    {
        {
            Injector.getApplicationComponent().inject(this);
        }
    }

    public void setup() {
        if (realmConfiguration == null) {
            Realm.init(mContext);
            realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
            Realm.setDefaultConfiguration(realmConfiguration);
        } else {
            throw new IllegalStateException("database already configured");
        }
    }

    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }

    public void addSetItems(ArrayList<SetItem> setItems) {
        Realm realm = getRealmInstance();
        RealmSetItems realmSetItems = realm.where(RealmSetItems.class).findFirst();
        if (realmSetItems == null) {
            realm.beginTransaction();
            RealmSetItems rsis = realm.createObject(RealmSetItems.class);
            rsis.setRealmSetItems(setItems);
            realm.commitTransaction();
        }
    }

    public <T extends RealmObject> T findFirst(Class<T> clazz) {
        return getRealmInstance().where(clazz).findFirst();
    }


    public void close() {
        getRealmInstance().close();
    }
}
