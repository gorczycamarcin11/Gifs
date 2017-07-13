package com.example.gifs.controller;

import com.example.gifs.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by marcin on 13.07.17.
 */
@RequestMapping("/")
@Controller
public class MainController {

    @Autowired
    private GifService gifServiceImpl;


    @GetMapping
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("gifs/list");
        mav.addObject("list", gifServiceImpl.getList());
        return mav;
    }
}
