package com.example.gifs.service;

import com.example.gifs.model.Gif;

import java.util.Set;

/**
 * Created by marcin on 13.07.17.
 */
public interface GifService {

    Set<Gif> getList();

    Gif findById(Long id) throws GifNotFoundException;

    void save(Gif gif);

    Gif findRandom() throws GifNotFoundException;

}
