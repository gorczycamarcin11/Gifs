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
    private GifDao gifDaoImpl;


    @Override
    public Set<Gif> getList() {
        return gifDaoImpl.findAll();
    }

    public Gif findById(Long id) throws GifNotFoundException {
        Gif gif = gifDaoImpl.findById(id);
        return getGifAndUpdate(gif);
    }

    @Override
    public void save(Gif gif) {
        gifDaoImpl.save(gif);
    }

    @Override
    public Gif findRandom() throws GifNotFoundException {
        Gif gif = gifDaoImpl.findRandom();
        return getGifAndUpdate(gif);
    }

    private Gif getGifAndUpdate(Gif gif) throws GifNotFoundException {
        if (gif == null) {
            throw new GifNotFoundException();
        } else {
            gif.increaseCount();
            gifDaoImpl.updateVisitCount(gif);
        }
        return gif;
    }
}
