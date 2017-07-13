package com.example.gifs.service;

import com.example.gifs.dao.GifDao;
import model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by marcin on 13.07.17.
 */
@Service
public class GifServiceImpl implements GifService {

    @Autowired
    private GifDao gifDaoStaticImpl;


    @Override
    public List<Gif> getList() {
        return gifDaoStaticImpl.findAll();
    }
}
