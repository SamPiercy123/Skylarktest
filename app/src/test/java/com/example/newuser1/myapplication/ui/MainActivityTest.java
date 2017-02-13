package com.example.newuser1.myapplication.ui;

import android.app.Activity;

import com.example.newuser1.myapplication.BuildConfig;
import com.example.newuser1.myapplication.RestTestApplicationTest;
import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.dependency_injection.ApplicationComponentTest;
import com.example.newuser1.myapplication.dependency_injection.ApplicationContextModuleTest;
import com.example.newuser1.myapplication.dependency_injection.DaggerApplicationComponentTest;
import com.example.newuser1.myapplication.dependency_injection.Injector;
import com.example.newuser1.myapplication.dependency_injection.NetworkComponent;
import com.example.newuser1.myapplication.dependency_injection.RealmModuleTest;
import com.example.newuser1.myapplication.services.RealmService;
import com.example.newuser1.myapplication.services.SharedPrefService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(application = RestTestApplicationTest.class, constants = BuildConfig.class, sdk = 21, packageName = "com.example.newuser1.myapplication")
@PowerMockIgnore({"org.robolectric.*", "android.*"})
@PrepareForTest({Injector.class})
public class MainActivityTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Inject
    SharedPrefService preferenceServiceMock;


    @Inject
    RealmService realmService;

    MainActivity activity;

    @Before
    public void setup() {
        ApplicationComponentTest applicationComponentTest = DaggerApplicationComponentTest.builder()
                .applicationContextModuleTest(new ApplicationContextModuleTest())
                .realmModuleTest(new RealmModuleTest(true))
                .networkComponent(Injector.getNetworkComponent())
                .build();

        PowerMockito.mockStatic(Injector.class);
        PowerMockito.when(Injector.getApplicationComponent()).thenReturn(applicationComponentTest);

        ((ApplicationComponentTest) Injector.getApplicationComponent()).inject(this);
    }

    //Network Component causing issues

 /*   private void setupActivity() {
        ButterKnife.setDebug(true);
        activity = Robolectric.setupActivity(MainActivity.class);
        ButterKnife.bind(this, activity);
    }



    @Test
    public void onCloseRealm() {
        doNothing().when(realmService).close();

        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        Activity myActivity = controller
                .create()
                .start()
                .resume()
                .visible()
                .get();
        ButterKnife.bind(this, myActivity);

        controller
                .pause()
                .stop()
                .destroy();
        verify(realmService).close();
    }
*/
}