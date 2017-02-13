package com.example.newuser1.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class EpisodeContent extends RealmObject implements Parcelable {

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("subtitle")
    @Expose
    private String subtitle;

    @SerializedName("image_urls")
    @Expose
    private RealmList<RealmString> imageUrls = null;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("modified")
    @Expose
    private String modified;

    @SerializedName("created_by")
    @Expose
    private String createdBy;

    @SerializedName("synopsis")
    @Expose
    private String synopsis;


    @SerializedName("image_obj")
    @Expose
    private ImageObj imageObj;

    public EpisodeContent()
    {

    }


    protected EpisodeContent(Parcel in) {
        uid = in.readString();
        subtitle = in.readString();
        title = in.readString();
        created = in.readString();
        modified = in.readString();
        createdBy = in.readString();
        synopsis = in.readString();
        imageObj = in.readParcelable(ImageObj.class.getClassLoader());
    }

    public static final Creator<EpisodeContent> CREATOR = new Creator<EpisodeContent>() {
        @Override
        public EpisodeContent createFromParcel(Parcel in) {
            return new EpisodeContent(in);
        }

        @Override
        public EpisodeContent[] newArray(int size) {
            return new EpisodeContent[size];
        }
    };

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


    public RealmList<RealmString> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(RealmList<RealmString> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public ImageObj getImageObj()
    {
        return imageObj;
    }

    public void setimageObj(ImageObj imageObj)
    {
        this.imageObj = imageObj;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(subtitle);
        dest.writeString(title);
        dest.writeString(created);
        dest.writeString(modified);
        dest.writeString(createdBy);
        dest.writeString(synopsis);
        dest.writeParcelable(imageObj, flags);
    }
}
