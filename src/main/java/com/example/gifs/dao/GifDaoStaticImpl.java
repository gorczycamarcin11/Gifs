package com.example.gifs.dao;

import com.example.gifs.model.Gif;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by marcin on 13.07.17.
 */

@Repository
public class GifDaoStaticImpl implements GifDao {

    private static final Set<Gif> GIFS = new HashSet<>();

    static {
        Gif g1 = new Gif();
        g1.setId(1L);
        g1.setTitle("Android explosion");
        g1.setDescription("Android explodes");
        g1.setTimestamp(LocalDateTime.now());
        g1.setVisitCount(0);
        g1.setImagePath("/gifs/android-explosion.gif");


        Gif g2 = new Gif();
        g2.setId(2L);
        g2.setTitle("Ben and Mike");
        g2.setDescription("Ben and mike");
        g2.setTimestamp(LocalDateTime.now());
        g2.setVisitCount(0);
        g2.setImagePath("/gifs/ben-and-mike.gif");


        Gif g3 = new Gif();
        g3.setId(3L);
        g3.setTitle("Book dominos");
        g3.setDescription("Book dominos");
        g3.setTimestamp(LocalDateTime.now());
        g3.setVisitCount(0);
        g3.setImagePath("/gifs/book-dominos.gif");


        Gif g4 = new Gif();
        g4.setId(4L);
        g4.setTitle("Compiler bot");
        g4.setDescription("Compiler bot");
        g4.setTimestamp(LocalDateTime.now());
        g4.setVisitCount(0);
        g4.setImagePath("/gifs/compiler-bot.gif");


        Gif g5 = new Gif();
        g5.setId(5L);
        g5.setTitle("Cowboy coder");
        g5.setDescription("Cowboy coder");
        g5.setTimestamp(LocalDateTime.now());
        g5.setVisitCount(0);
        g5.setImagePath("/gifs/cowboy-coder.gif");


        Gif g6 = new Gif();
        g6.setId(6L);
        g6.setTitle("Infinite Andrew");
        g6.setDescription("Infinite Andrew");
        g6.setTimestamp(LocalDateTime.now());
        g6.setVisitCount(0);
        g6.setImagePath("/gifs/infinite-andrew.gif");


        Gif g7 = new Gif();
        g7.setId(7L);
        g7.setTitle("Rickroll");
        g7.setDescription("Rickroll");
        g7.setTimestamp(LocalDateTime.now());
        g7.setVisitCount(0);
        g7.setImagePath("/gifs/rickroll.gif");

//        GIFS.add(g1);
//        GIFS.add(g2);
//        GIFS.add(g3);
//        GIFS.add(g4);
//        GIFS.add(g5);
//        GIFS.add(g6);
//        GIFS.add(g7);

    }


    @Override
    public Set<Gif> findAll() {
        return GIFS;
    }

    @Override
    public Gif findById(Long id) {
        for (Gif gif : GIFS) {
            if (gif.getId().equals(id)) {
                return gif;
            }
        }
        return null;
    }

    @Override
    public void updateVisitCount(Gif gif) {
        GIFS.add(gif);
    }

    @Override
    public void save(Gif gif) {
        long newId = GIFS.size() + 1;
        gif.setId(newId);
        GIFS.add(gif);
    }

    @Override
    public Gif findRandom() {

        int maxId = GIFS.size();
        Random random = new Random();
        long randomId = random.nextInt(maxId) + 1;
        return findById(randomId);
    }

}
