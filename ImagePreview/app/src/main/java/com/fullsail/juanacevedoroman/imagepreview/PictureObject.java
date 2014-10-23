package com.fullsail.juanacevedoroman.imagepreview;

import java.io.Serializable;

/**
 * Created by juanacevedoroman on 10/23/14.
 */
public class PictureObject implements Serializable {

    public static final long serialVersionUID = 123456789L;

    String pictureUrl;
    String pictureName;

    public PictureObject(String _name, String _url){

        pictureName =  _name;
        pictureUrl = _url;

    }

}
