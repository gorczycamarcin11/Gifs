package com.example.gifs.dao;

import model.Gif;

import java.util.List;

/**
 * Created by marcin on 13.07.17.
 */
public interface GifDao {
    List<Gif> findAll();
}
