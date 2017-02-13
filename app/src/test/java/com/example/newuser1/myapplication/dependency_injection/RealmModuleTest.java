package com.example.newuser1.myapplication.dependency_injection;

import com.example.newuser1.myapplication.services.RealmService;
import com.example.newuser1.myapplication.services.RealmServiceTest;

import org.junit.Test;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

@Module
public class RealmModuleTest {

    private boolean isMockRealm;


    public RealmModuleTest(boolean isMockRealm) {
        this.isMockRealm = isMockRealm;
    }

    @Provides
    @CustomScope
    public RealmService provideRealmService()
    {
        return isMockRealm ? mock(RealmService.class) : new RealmService();
    }

}