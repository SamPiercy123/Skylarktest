package com.example.newuser1.myapplication.models;

import io.realm.RealmObject;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class RealmString extends RealmObject {

    private String string;

    public RealmString()
    {


    }

    public RealmString(String string)
    {
        this.string = string;
    }

    public String getValue()
    {
        return string;
    }

    public void setValue(String value) {
        this.string = value;
    }

}
