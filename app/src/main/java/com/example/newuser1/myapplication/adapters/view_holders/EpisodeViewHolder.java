package com.example.newuser1.myapplication.adapters.view_holders;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newuser1.myapplication.R;
import com.example.newuser1.myapplication.models.EpisodeContent;
import com.example.newuser1.myapplication.models.ImageObj;
import com.example.newuser1.myapplication.models.SetItem;
import com.example.newuser1.myapplication.services.SharedPrefService;
import com.example.newuser1.myapplication.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class EpisodeViewHolder extends BaseViewHolder
{


    private EpisodeContent episodeContent;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.star)
    ImageView star;
    @OnClick(R.id.star)
    void onClick()
    {
        Context context = itemView.getContext();

        if(context instanceof MainActivity)
        {
            SharedPrefService sps = ((MainActivity) context).getSharedPrefService();
            Boolean isFavRightNow = sps.isEpisodeFavourite(episodeContent.getUid());

            sps.saveEpisodeFavouritePref(episodeContent.getUid(), !isFavRightNow);

            if(isFavRightNow)
            {
                Glide.with(context)
                        .load(R.drawable.star_gray)
                        .into(star);
                Toast.makeText(context, context.getString(R.string.episode_unfavourited), Toast.LENGTH_SHORT).show();
            }
            else
            {
                Glide.with(context)
                        .load(R.drawable.star_gold)
                        .into(star);
                Toast.makeText(context, context.getString(R.string.episode_favourited), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public EpisodeViewHolder(final View itemView)
    {

        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onItemClickListener.onItemClick(episodeContent);
            }
        });
    }

    @Override
    public void onViewHolderBound(SetItem model, int position)
    {

        episodeContent = model.getEpisodeContent();

        title.setText(episodeContent.getTitle());

        ImageObj imageObj = (episodeContent.getImageObj());

        Context context = itemView.getContext();


        if(imageObj != null)
        {
            Glide.with(context)
                    .load(imageObj.getUrl())
                    .centerCrop()
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(image);
        }
        else
        {
            Glide.with(context)
                    .load(R.drawable.placeholder)
                    .centerCrop()
                    .into(image);
        }

        if(context instanceof MainActivity)
        {
            SharedPrefService sps = ((MainActivity) context).getSharedPrefService();
            if(sps.isEpisodeFavourite(episodeContent.getUid()))
            {
                Glide.with(context)
                        .load(R.drawable.star_gold)
                        .into(star);
            }
            else
            {
                Glide.with(context)
                        .load(R.drawable.star_gray)
                        .into(star);
            }
        }


    }

    public EpisodeContent getEpisodeContent()
    {
        return episodeContent;
    }
}
