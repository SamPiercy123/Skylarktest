package com.example.newuser1.myapplication.services;

import com.example.newuser1.myapplication.models.ImageObj;
import com.example.newuser1.myapplication.models.Sets;

import rx.Observable;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public interface ImageObjDLInterface {

    void onImageObjDLCompleted();

    void onImageObjDLError(Throwable e);

    void onNext(ImageObj imageObj);

    Observable<ImageObj> getImageObj(String setExt);
}
