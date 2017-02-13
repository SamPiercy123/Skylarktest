package com.example.newuser1.myapplication.adapters;

/**
 * Created by Sam Piercy on 11/02/2017.
 */


import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newuser1.myapplication.adapters.view_holders.BaseViewHolder;
import com.example.newuser1.myapplication.models.EpisodeContent;
import com.example.newuser1.myapplication.models.SetItem;
import com.example.newuser1.myapplication.services.Listeners;

import java.util.HashSet;


public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder>
{
    protected LayoutInflater mInflater;
    protected Listeners.OnItemClickListener<EpisodeContent> onItemClickListener;


    protected abstract SetItem getItem(int position);

    @Override
    public final int getItemViewType(int position)
    {
        return getItem(position).getViewType();
    }

    @Override
    public final void onBindViewHolder(BaseViewHolder holder, int position)
    {
        holder.onViewHolderBound(getItem(position), position);
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(mInflater == null)
            mInflater = LayoutInflater.from(parent.getContext());

        View view = mInflater.inflate(viewType, parent, false);
        BaseViewHolder viewHolder = createViewHolderForType(view, viewType);
        viewHolder.setOnItemClickListener(onItemClickListener);


        return viewHolder;
    }

    protected abstract BaseViewHolder createViewHolderForType(View itemView, int viewType);


    public void setOnItemClickListener(Listeners.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
}
