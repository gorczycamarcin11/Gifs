package com.example.gifs.dao;

import com.example.gifs.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by marcin on 16.07.17.
 */
@Primary
@Repository
public class GifDaoImplCrud implements GifDao {


    @Autowired
    private GifDaoCrud gifDaoCrud;


    @Override
    public Set<Gif> findAll() {
        Iterable<Gif> collection = gifDaoCrud.findAll();
        Set<Gif> gifs = new HashSet<>();
        collection.forEach(gifs::add);
        return gifs;
    }


    @Override
    public Gif findById(Long id) {
        return gifDaoCrud.findOne(id);
    }

    @Override
    public void updateVisitCount(Gif gif) {
        this.save(gif);
    }

    @Override
    public void save(Gif gif) {
        gifDaoCrud.save(gif);
    }

    @Override
    public Gif findRandom() {
        List<Long> allIds = gifDaoCrud.findAllIds();
        int listSize = allIds.size();
        Random random = new Random();
        int randomIndex = random.nextInt(listSize);

        if (randomIndex < 0) {
            return null;
        } else {
            long randomID = allIds.get(randomIndex);
            return findById(randomID);
        }
    }
}
