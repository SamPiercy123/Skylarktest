package com.example.newuser1.myapplication.models;

/**
 * Created by Sam Piercy on 11/02/2017.
 */


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sets{

    @SerializedName("objects")
    @Expose
    private List<SetHead> setheads = null;

    public List<SetHead> getSetheads() {
        return setheads;
    }

    public void setSetheads(List<SetHead> setheads) {
        this.setheads = setheads;
    }

}
