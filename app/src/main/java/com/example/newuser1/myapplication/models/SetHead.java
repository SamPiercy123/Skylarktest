package com.example.newuser1.myapplication.models;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetHead {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("items")
    @Expose
    private List<SetItem> items = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SetItem> getItems() {
        return items;
    }

    public void setItems(List<SetItem> items) {
        this.items = items;
    }


}
