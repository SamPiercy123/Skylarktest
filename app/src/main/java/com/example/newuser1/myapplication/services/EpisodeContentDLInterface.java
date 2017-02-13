package com.example.newuser1.myapplication.services;

import com.example.newuser1.myapplication.models.EpisodeContent;
import com.example.newuser1.myapplication.models.Sets;

import rx.Observable;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public interface EpisodeContentDLInterface {

    void onEpisodeContentDLCompleted();

    void onEpisodeContentDLError(Throwable e);

    void onNext(EpisodeContent episodeContent);

    Observable<EpisodeContent> getEpisodeContent(String setExt);

}
