package com.example.newuser1.myapplication.services;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.dependency_injection.Injector;
import com.example.newuser1.myapplication.models.Consts;
import com.example.newuser1.myapplication.models.RealmSetItems;
import com.example.newuser1.myapplication.models.SetItem;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class SharedPrefService {

    SharedPreferences sharedPreferences;

    public SharedPrefService(Application application)
    {
        this.sharedPreferences = application.getSharedPreferences(Consts.PREF_NAME, Context.MODE_PRIVATE);
    }

    /*public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }*/

    public boolean isEpisodeFavourite(String uid)
    {

        return sharedPreferences.getBoolean(uid, false);

    }

    public void saveEpisodeFavouritePref(String uid, boolean isFavourite)
    {
        sharedPreferences.edit().putBoolean(uid, isFavourite).commit();
    }

}
