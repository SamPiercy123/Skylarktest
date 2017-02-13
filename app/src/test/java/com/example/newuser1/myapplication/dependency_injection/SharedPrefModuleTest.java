package com.example.newuser1.myapplication.dependency_injection;

import com.example.newuser1.myapplication.services.SharedPrefService;
import com.example.newuser1.myapplication.services.SharedPrefServiceTest;

import dagger.Module;
import dagger.Provides;

import static org.junit.Assert.*;

/**
 * Created by Sam Piercy on 11/02/2017.
 */
@Module
public class SharedPrefModuleTest {


    @Provides @CustomScope
    public SharedPrefServiceTest provideSharedPrefService() {
            return new SharedPrefServiceTest();
        }

}