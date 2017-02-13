package com.example.newuser1.myapplication.dependency_injection;

import com.example.newuser1.myapplication.services.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


/**
 * Created by Sam Piercy on 11/02/2017.
 */

@Module
public class RestModule
{
    @Provides
    @CustomScope
    RestService provideRestService(Retrofit retrofit)
    {
        return retrofit.create(RestService.class);
    }
}
