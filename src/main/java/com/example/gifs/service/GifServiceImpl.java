package com.example.gifs.service;

import com.example.gifs.dao.GifDao;
import com.example.gifs.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by marcin on 13.07.17.
 */
@Service
public class GifServiceImpl implements GifService {

    @Autowired
    private GifDao gifDaoStaticImpl;


    @Override
    public Set<Gif> getList() {
        return gifDaoStaticImpl.findAll();
    }

    public Gif findById(Long id) throws GifNotFoundException {
        Gif gif = gifDaoStaticImpl.findById(id);
        if (gif == null) {
            throw new GifNotFoundException();
        }else{
            gif.increaseCount();

        }
        return gif;
    }

    @Override
    public void save(Gif gif) {
        gifDaoStaticImpl.save(gif);
    }
}
