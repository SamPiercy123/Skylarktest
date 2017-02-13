package com.example.newuser1.myapplication.dependency_injection;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.services.RealmService;
import com.example.newuser1.myapplication.services.SharedPrefService;
import com.example.newuser1.myapplication.ui.EpisodeActivity;
import com.example.newuser1.myapplication.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

@CustomScope
@Component(modules = {ApplicationContextModule.class, RealmModule.class, RestModule.class}, dependencies = NetworkComponent.class)
public interface ApplicationComponent {

    void inject(RealmService realmService);

    void inject(RestTestApplication application);

    void inject(MainActivity mainActivity);

    void inject(EpisodeActivity episodeActivity);


}
