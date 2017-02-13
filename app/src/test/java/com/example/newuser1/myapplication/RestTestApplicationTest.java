package com.example.newuser1.myapplication;

import com.example.newuser1.myapplication.application.RestTestApplication;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class RestTestApplicationTest extends RestTestApplication
{
    @Override
    protected void initRealm()
    {
        //do nothing here for testing to prevent crash.
    }
}
