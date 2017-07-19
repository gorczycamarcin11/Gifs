package com.example.gifs.controller;

import com.example.gifs.model.Comment;
import com.example.gifs.model.Gif;
import com.example.gifs.service.CommentService;
import com.example.gifs.service.GifNotFoundException;
import com.example.gifs.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by marcin on 19.07.17.
 */
@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private GifService gifServiceImpl;

    @Autowired
    private CommentService commentServiceImpl;


    @PostMapping
    public ModelAndView saveComment(@Valid Comment comment, BindingResult bindingResult, @RequestParam("gifId") Long gifId) throws GifNotFoundException {

        Gif gif = gifServiceImpl.findById(gifId);
        System.out.println(bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("gifs/details");
            mav.addObject("gif", gif);
            mav.addObject("comment", comment);
            return mav;
        } else {
            comment.setGif(gif);
            //save comment
            commentServiceImpl.save(comment);
            return new ModelAndView("redirect:gif/" + gifId);
        }
    }
}
