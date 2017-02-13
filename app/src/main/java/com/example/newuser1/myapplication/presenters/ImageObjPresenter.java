package com.example.newuser1.myapplication.presenters;

import com.example.newuser1.myapplication.models.ImageObj;
import com.example.newuser1.myapplication.models.Sets;
import com.example.newuser1.myapplication.services.ImageObjDLInterface;
import com.example.newuser1.myapplication.services.SetsDLInterface;

import rx.Observer;

/**
 * Created by Sam Piercy on 11/02/2017.
 */

public class ImageObjPresenter extends BasePresenter implements Observer<ImageObj> {

    private ImageObjDLInterface imageObjDLInterface;

    public ImageObjPresenter(ImageObjDLInterface imageObjDLInterface)
    {
        this.imageObjDLInterface = imageObjDLInterface;
    }


    @Override
    public void onCompleted()
    {
        imageObjDLInterface.onImageObjDLCompleted();
    }

    @Override
    public void onError(Throwable e)
    {
        imageObjDLInterface.onImageObjDLError(e);
    }

    @Override
    public void onNext(ImageObj imageObj)
    {
        imageObjDLInterface.onNext(imageObj);

    }

    public void fetchImageObj(String ext)
    {
        unsubscribeAll();
        ext = ext.substring(1); //remove the first '/' to work with retrofit2.
        subscribe(imageObjDLInterface.getImageObj(ext) , ImageObjPresenter.this);
    }

}
