package com.example.gifs.dao;

import com.example.gifs.model.Gif;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marcin on 16.07.17.
 */
@Repository
public interface GifDaoCrud extends CrudRepository<Gif, Long> {

    @Query("SELECT g.id FROM Gif g")
    List<Long> findAllIds();

    @Query("SELECT g FROM Gif g ORDER BY g.timestamp DESC")
    List<Gif> findAllOrderByTimestampDesc();
}
