package com.example.newuser1.myapplication.dependency_injection;

import com.example.newuser1.myapplication.services.RealmService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sam Piercy on 11/02/2017.
 */


@Module
public class RealmModule {

    @Provides
    @CustomScope
    public RealmService provideRealmService() {
        return new RealmService();
    }
}
