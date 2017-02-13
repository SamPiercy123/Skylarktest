package com.example.newuser1.myapplication.adapters.view_holders;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.newuser1.myapplication.models.EpisodeContent;
import com.example.newuser1.myapplication.models.SetItem;
import com.example.newuser1.myapplication.services.Listeners;

/**
 * Created by Sam Piercy on 11/02/2017.
 */


public abstract class BaseViewHolder extends RecyclerView.ViewHolder
{
    protected Listeners.OnItemClickListener<EpisodeContent> onItemClickListener;

    BaseViewHolder(View itemView)
    {
        super(itemView);
    }

    /**
     * A required method which handles the binding of the source data to the view
     * inflated in {@link RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}
     * The particular instance extending BaseModel can be safely cast in any subclasses of
     * this class to the required subtype
     *
     * @param model    The instance of BaseModel from the adapter's datasource
     * @param position
     */
    public abstract void onViewHolderBound(SetItem model, int position);


    public void setOnItemClickListener(Listeners.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
}
