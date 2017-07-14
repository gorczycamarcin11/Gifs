package com.example.gifs.service;

/**
 * Created by marcin on 14.07.17.
 */
public class GifNotFoundException extends Exception {

    public GifNotFoundException(){
        super("Gif not found");
    }
}
