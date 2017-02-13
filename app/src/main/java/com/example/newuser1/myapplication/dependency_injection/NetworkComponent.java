package com.example.newuser1.myapplication.dependency_injection;


import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Sam Piercy on 11/02/2017.
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent
{
    Retrofit retrofit();
}
