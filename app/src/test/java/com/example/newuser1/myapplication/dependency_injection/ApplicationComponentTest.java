package com.example.newuser1.myapplication.dependency_injection;

import android.app.Application;

import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.services.RealmServiceTest;
import com.example.newuser1.myapplication.services.RestService;
import com.example.newuser1.myapplication.services.SharedPrefService;
import com.example.newuser1.myapplication.services.SharedPrefServiceTest;
import com.example.newuser1.myapplication.ui.EpisodeActivity;
import com.example.newuser1.myapplication.ui.EpisodeActivityTest;
import com.example.newuser1.myapplication.ui.MainActivity;
import com.example.newuser1.myapplication.ui.MainActivityTest;

import org.junit.Test;

import javax.inject.Singleton;

import dagger.Component;

import static org.junit.Assert.*;

/**
 * Created by Sam Piercy on 11/02/2017.
 */
@CustomScope
@Component(modules = {ApplicationContextModuleTest.class, RealmModuleTest.class,
        SharedPrefModuleTest.class, RestModule.class}, dependencies = NetworkComponent.class)

public interface ApplicationComponentTest extends ApplicationComponent
{

    void inject(Application application);

    void inject(MainActivityTest activity);

    void inject (EpisodeActivityTest eat);

    void inject (RealmServiceTest rst);

}

