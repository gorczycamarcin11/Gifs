package com.example.gifs.service;

import com.example.gifs.dao.CommentDao;
import com.example.gifs.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by marcin on 19.07.17.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;


    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }
}
