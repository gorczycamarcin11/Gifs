package com.example.gifs.dao;

import com.example.gifs.model.Gif;

import java.util.Set;

/**
 * Created by marcin on 13.07.17.
 */
public interface GifDao {
    Set<Gif> findAll();
    Gif findById(Long id);
    void updateVisitCount(Gif gif);
    void save(Gif gif);
}
