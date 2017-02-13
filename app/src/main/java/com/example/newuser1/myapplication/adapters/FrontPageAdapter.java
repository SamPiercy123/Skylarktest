package com.example.newuser1.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.newuser1.myapplication.R;
import com.example.newuser1.myapplication.adapters.view_holders.BaseViewHolder;
import com.example.newuser1.myapplication.adapters.view_holders.DividerViewHolder;
import com.example.newuser1.myapplication.adapters.view_holders.EpisodeViewHolder;
import com.example.newuser1.myapplication.models.SetItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class FrontPageAdapter extends BaseRecyclerAdapter
{

    private List<SetItem> models;

    public FrontPageAdapter (List<SetItem> models)
    {
        this.models = models;
    }


    @Override
    protected SetItem getItem(int position)
    {
        return models.get(position);
    }

    @Override
    protected BaseViewHolder createViewHolderForType(View itemView, int viewType)
    {

        switch (viewType)
        {
            case R.layout.item_divider:
            default:
                return new DividerViewHolder(itemView);
            case R.layout.item_episode:
                return new EpisodeViewHolder(itemView);
        }
    }

    @Override
    public int getItemCount()
    {
        return models.size();
    }
}
