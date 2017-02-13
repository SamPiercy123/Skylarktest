package com.example.newuser1.myapplication.adapters.view_holders;

import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.example.newuser1.myapplication.R;
import com.example.newuser1.myapplication.models.SetItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class DividerViewHolder extends BaseViewHolder
{

    @BindView(R.id.header)
    TextView header;

    public DividerViewHolder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onViewHolderBound(SetItem model, int position)
    {
        SetItem setItem = (SetItem) model;
        header.setText(((SetItem) model).getHeading());
    }
}
