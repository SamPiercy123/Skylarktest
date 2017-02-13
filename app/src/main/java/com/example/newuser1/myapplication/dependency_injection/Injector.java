package com.example.newuser1.myapplication.dependency_injection;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.models.Consts;

import java.util.Objects;

public class Injector {

    private static ApplicationComponent applicationComponent;

    private Injector() {}

    public static void initializeApplicationComponent(RestTestApplication restTestApplication)
    {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationContextModule(new ApplicationContextModule(restTestApplication))
                .realmModule(new RealmModule())
                .restModule(new RestModule())
                .networkComponent(getNetworkComponent())
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        if(applicationComponent == null)
        {
            throw new NullPointerException("applicationComponent is null");
        }

        return applicationComponent;
    }

    public static NetworkComponent getNetworkComponent()
    {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Consts.BASE_URL))
                .build();
    }

}
