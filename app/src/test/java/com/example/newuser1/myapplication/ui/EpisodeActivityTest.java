package com.example.newuser1.myapplication.ui;

import android.graphics.drawable.Drawable;

import com.example.newuser1.myapplication.BuildConfig;
import com.example.newuser1.myapplication.RestTestApplicationTest;
import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.dependency_injection.ApplicationComponentTest;
import com.example.newuser1.myapplication.dependency_injection.ApplicationContextModuleTest;
import com.example.newuser1.myapplication.dependency_injection.DaggerApplicationComponentTest;
import com.example.newuser1.myapplication.dependency_injection.Injector;
import com.example.newuser1.myapplication.dependency_injection.RealmModuleTest;
import com.example.newuser1.myapplication.services.RealmService;
import com.example.newuser1.myapplication.services.SharedPrefService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Sam Piercy on 11/02/2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = RestTestApplicationTest.class, constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({"org.robolectric.*", "android.*"})
@PrepareForTest({Injector.class})
public class EpisodeActivityTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Inject
    SharedPrefService preferenceServiceMock;


    @Inject
    RealmService realmService;

    EpisodeActivity activity;

    String UID = "youeyedee";

    @Before
    public void setup() {
        ApplicationComponentTest applicationComponentTest = DaggerApplicationComponentTest.builder()
                .applicationContextModuleTest(new ApplicationContextModuleTest())
                .realmModuleTest(new RealmModuleTest(true))
                .networkComponent(Injector.getNetworkComponent())
                .build();

        PowerMockito.mockStatic(Injector.class);
        when(Injector.getApplicationComponent()).thenReturn(applicationComponentTest);

        ((ApplicationComponentTest) Injector.getApplicationComponent()).inject(this);
    }

    private void setupActivity() {
        ButterKnife.setDebug(true);
        activity = Robolectric.setupActivity(EpisodeActivity.class);
        ButterKnife.bind(this, activity);
    }


    /*@Test
    public void onClick_shouldChangeImage() {

        setupActivity();

        Drawable drawable = activity.image.getDrawable();
        activity.star.performClick();
        assertTrue(drawable != activity.image.getDrawable());
    }*/

/*    @Test
    public void shouldInitPreference()
    {

        when(preferenceServiceMock.isEpisodeFavourite(UID)).thenReturn(true);
        setupActivity();

        verify(preferenceServiceMock, times(1)).isEpisodeFavourite(UID);
    }*/

    @Test
    public void onClick_shouldNotEditEmptyPreference() {
        String MY_PREFERENCE = "";

        doNothing().when(preferenceServiceMock).saveEpisodeFavouritePref(UID, true);
        setupActivity();

        activity.title.setText(MY_PREFERENCE);
        activity.title.performClick();
        verify(preferenceServiceMock, never()).saveEpisodeFavouritePref(UID, true);
    }

 /*   @Test
    public void onClick_shouldEditPreference() {

        doNothing().when(preferenceServiceMock).saveEpisodeFavouritePref(UID, true);

        Mockito.when(preferenceServiceMock).isEpisodeFavourite(UID) = true;

        setupActivity();

        activity.star.performClick();

        verify(preferenceServiceMock).saveEpisodeFavouritePref(UID, true);

    }*/

    @Test
    public void onBackPressWontKillActivityImmediately()
    {
        setupActivity();
        activity.onBackPressed();
        assertTrue(activity != null);
    }



}