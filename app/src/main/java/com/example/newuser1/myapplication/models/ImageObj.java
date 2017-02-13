package com.example.newuser1.myapplication.models;

/**
 * Created by Sam Piercy on 11/02/2017.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ImageObj extends RealmObject implements Parcelable{
    

    @SerializedName("url")
    @Expose
    private String url;

    public ImageObj()
    {

    }


    protected ImageObj(Parcel in) {

        url = in.readString();

    }

    public static final Creator<ImageObj> CREATOR = new Creator<ImageObj>() {
        @Override
        public ImageObj createFromParcel(Parcel in) {
            return new ImageObj(in);
        }

        @Override
        public ImageObj[] newArray(int size) {
            return new ImageObj[size];
        }
    };


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }
}
