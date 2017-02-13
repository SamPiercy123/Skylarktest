package com.example.newuser1.myapplication.services;

import com.example.newuser1.myapplication.BuildConfig;
import com.example.newuser1.myapplication.RestTestApplicationTest;
import com.example.newuser1.myapplication.application.RestTestApplication;
import com.example.newuser1.myapplication.dependency_injection.ApplicationComponentTest;
import com.example.newuser1.myapplication.dependency_injection.ApplicationContextModuleTest;
import com.example.newuser1.myapplication.dependency_injection.DaggerApplicationComponent;
import com.example.newuser1.myapplication.dependency_injection.DaggerApplicationComponentTest;
import com.example.newuser1.myapplication.dependency_injection.Injector;
import com.example.newuser1.myapplication.dependency_injection.RealmModuleTest;
import com.example.newuser1.myapplication.models.ImageObj;
import com.example.newuser1.myapplication.models.RealmSetItems;
import com.example.newuser1.myapplication.models.SetItem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Sam Piercy on 11/02/2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = RestTestApplicationTest.class, constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({"org.robolectric.*", "android.*"})
@PrepareForTest({Realm.class, Injector.class, RealmQuery.class, RealmResults.class})
public class RealmServiceTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Inject
    RealmService realmService;

    Realm realmMock;

    @Before
    public void setupRealm() {
        ApplicationComponentTest applicationComponentTest = DaggerApplicationComponentTest.builder()
                .applicationContextModuleTest(new ApplicationContextModuleTest())
                .realmModuleTest(new RealmModuleTest(false))
                .networkComponent(Injector.getNetworkComponent())
                .build();

        mockStatic(Injector.class);
        when(Injector.getApplicationComponent()).thenReturn(applicationComponentTest);

        ((ApplicationComponentTest) Injector.getApplicationComponent()).inject(this);

        realmMock = mock(Realm.class);
        mockStatic(Realm.class);
        when(Realm.getDefaultInstance()).thenReturn(realmMock);
    }
/*
    @Test
    public void test_add() {
        ArrayList<SetItem> setItems= new ArrayList<>();

        setItems.add(new SetItem());
        setItems.add(new SetItem());


        doNothing().when(realmMock).beginTransaction();
        doNothing().when(realmMock).commitTransaction();
        doNothing().when(realmMock).createObject(RealmSetItems.class);
        realmService.addSetItems(setItems);

        assertThat(Realm.getDefaultInstance(), is(realmMock));
        verify(realmMock).beginTransaction();
        verify(realmMock).commitTransaction();
    }*/


    @Test
    public void test_findFirst() {
        ImageObj imageObj1 = new ImageObj();
        ImageObj imageObj2 = new ImageObj();
        List imageList = Arrays.asList(imageObj1, imageObj2);
        RealmResults<ImageObj> messages = mockRealmResults();

        RealmQuery<ImageObj> query = mockRealmQuery();
        when(realmMock.where(ImageObj.class)).thenReturn(query);
        when(query.findAll()).thenReturn(messages);
        when(messages.iterator()).thenReturn(imageList.iterator());
        when(messages.size()).thenReturn(imageList.size());

        ImageObj result = realmService.findFirst(ImageObj.class);

        assertThat(Realm.getDefaultInstance(), is(realmMock));
        verify(realmMock.where(ImageObj.class), times(1)).findFirst();
    }

    @SuppressWarnings("unchecked")
    private <T extends RealmObject> RealmQuery<T> mockRealmQuery() {
        return mock(RealmQuery.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends RealmObject> RealmResults<T> mockRealmResults() {
        return mock(RealmResults.class);
    }




}