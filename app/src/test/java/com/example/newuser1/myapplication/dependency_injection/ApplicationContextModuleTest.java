package com.example.newuser1.myapplication.dependency_injection;

import android.app.Application;
import android.content.Context;

import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.services.RestService;
import com.example.newuser1.myapplication.services.SharedPrefService;

import org.robolectric.RuntimeEnvironment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import static org.mockito.Mockito.mock;

/**
 * Created by Sam Piercy on 11/02/2017.
 */
@Module
public class ApplicationContextModuleTest
{

    /*@Provides
    @CustomScope
    public RestTestApplication application() {
        return (RestTestApplication) RuntimeEnvironment.application.getApplicationContext();
    }*/

    @Provides
    @CustomScope
    public Context applicationContext() {
        return RuntimeEnvironment.application.getApplicationContext();
    }

    @Provides
    @CustomScope
    public SharedPrefService getAppPreferences() {
        return mock(SharedPrefService.class);
    }

}