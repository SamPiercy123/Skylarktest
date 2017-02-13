package com.example.newuser1.myapplication.models;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

        import android.os.Parcel;
        import android.os.Parcelable;
        import android.util.Log;

        import com.example.newuser1.myapplication.R;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import io.realm.RealmModel;
        import io.realm.RealmObject;
        import io.realm.annotations.RealmClass;


public class SetItem extends RealmObject implements  Parcelable {


    @SerializedName("content_url")
    @Expose
    private String contentUrl;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @SerializedName("content_type")
    @Expose
    private String contentType;
    @SerializedName("heading")
    @Expose
    private String heading;

    @SerializedName("episode_content")
    @Expose
    private EpisodeContent episodeContent;

    public SetItem()
    {

    }

    protected SetItem(Parcel in) {
        contentUrl = in.readString();
        contentType = in.readString();
        heading = in.readString();
        episodeContent = in.readParcelable(EpisodeContent.class.getClassLoader());
    }

    public static final Creator<SetItem> CREATOR = new Creator<SetItem>() {
        @Override
        public SetItem createFromParcel(Parcel in) {
            return new SetItem(in);
        }

        @Override
        public SetItem[] newArray(int size) {
            return new SetItem[size];
        }
    };

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public EpisodeContent getEpisodeContent()
    {
        return episodeContent;
    }

    public void setEpisodeContent(EpisodeContent episodeContent)
    {
        this.episodeContent = episodeContent;
    }

    public int getViewType() {
        if(contentType.equals(Consts.EPISODE))
            return R.layout.item_episode;
        else if (contentType.equals(Consts.DIVIDER))
            return R.layout.item_divider;
        else return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contentUrl);
        dest.writeString(contentType);
        dest.writeString(heading);
        dest.writeParcelable(episodeContent, flags);
    }


}
