package com.example.newuser1.myapplication.models;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class RealmSetItems extends RealmObject{

    private RealmList<SetItem> setItems;

    public RealmSetItems()
    {
        setItems = new RealmList<>();
    }

    public RealmList<SetItem> getSetItems() {
        return setItems;
    }

    public void setRealmSetItems(ArrayList<SetItem> setItems) {
        this.setItems.addAll(setItems);
    }



}
