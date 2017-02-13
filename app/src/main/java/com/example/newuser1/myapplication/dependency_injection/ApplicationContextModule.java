package com.example.newuser1.myapplication.dependency_injection;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.models.Consts;
import com.example.newuser1.myapplication.services.SharedPrefService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

@Module
public class ApplicationContextModule {

    private final RestTestApplication application;

    public ApplicationContextModule(RestTestApplication application) {
        this.application = application;
    }

    @Provides
    @CustomScope
    public RestTestApplication application() {
        return application;
    }

    @Provides
    @CustomScope
    public Context applicationContext() {
        return application.getApplicationContext();
    }

    @CustomScope
    @Provides
    public SharedPrefService getAppPreferences() {
        return new SharedPrefService(application);
    }


}