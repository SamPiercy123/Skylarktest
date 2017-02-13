package com.example.newuser1.myapplication.services;

import android.content.SharedPreferences;

import com.example.newuser1.myapplication.BuildConfig;
import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.dependency_injection.Injector;
import com.example.newuser1.myapplication.dependency_injection.SharedPrefModuleTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.annotation.Config;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import org.robolectric.*;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Sam Piercy on 11/02/2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = RestTestApplication.class, constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({"org.robolectric.*", "android.*"})
@PrepareForTest({SharedPreferences.class, Injector.class})
public class SharedPrefServiceTest {

//    @Rule
//    public PowerMockRule rule = new PowerMockRule();
//
//    @Inject SharedPrefService sharedPrefService;
//
//    SharedPreferences sharedPreferencesMock;
//
//    String TEST_STRING = "stringaling";
//
//    @Before
//    public void setupShared()
//    {
//        ApplicationComponentTest applicationComponentTest = DaggerApplicationComponentTest.builder()
//                .applicationContextModuleTest(new ApplicationContextModuleTest())
//                .sharedPrefModuleTest(new SharedPrefModuleTest())
//                .build();
//
//        mockStatic(Injector.class);
//        when(Injector.getApplicationComponent()).thenReturn(applicationComponentTest);
//
//        ((ApplicationComponentTest) Injector.getApplicationComponent()).inject(this);
//
//        sharedPreferencesMock = mock(SharedPreferences.class);
//        mockStatic(SharedPreferences.class);
//
//    }
//
//    @Test
//    public void  testSave(){
//       Boolean bool = true;
//        String id = TEST_STRING;
//
//        doNothing().when(sharedPreferencesMock).edit()
//                .putBoolean(id, bool)
//                .commit();
//
//        sharedPrefService.saveEpisodeFavouritePref(id, bool);
//
//        verify(sharedPreferencesMock).edit()
//                .putBoolean(id, bool)
//                .commit();
//    }
//
//



}