package com.example.newuser1.myapplication.services;

import com.example.newuser1.myapplication.models.Consts;
import com.example.newuser1.myapplication.models.EpisodeContent;
import com.example.newuser1.myapplication.models.ImageObj;
import com.example.newuser1.myapplication.models.Sets;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sam.piercy on 11/02/2017.
 */
public interface RestService
{
    @GET("{id}")
    Observable<Sets> getSets(@Path(Consts.ID) String setExt, @Query(Consts.SLUG) String slug);

    @GET("{id}")
    Observable<EpisodeContent> getEpisode(@Path(Consts.ID) String setExt);

    @GET("{id}")
    Observable<ImageObj> getImageObj(@Path(Consts.ID) String setExt);

}
