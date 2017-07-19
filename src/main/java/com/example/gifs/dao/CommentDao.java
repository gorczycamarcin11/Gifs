package com.example.gifs.dao;

import com.example.gifs.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marcin on 19.07.17.
 */
@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {

}
