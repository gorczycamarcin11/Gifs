package com.example.gifs.controller;

import com.example.gifs.model.Gif;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by marcin on 15.07.17.
 */
@RequestMapping("/add")
@Controller
public class AddGifController {

    private static Logger LOG = LoggerFactory.getLogger(AddGifController.class);


    @GetMapping
    public ModelAndView add() {

        ModelAndView mav = new ModelAndView("gifs/add");
        mav.addObject("gif", new Gif());
        return mav;
    }

    @PostMapping
    public ModelAndView add(@RequestParam("file") MultipartFile file, Gif gif){
        LOG.info("File received {}", file);
        LOG.info("Gif received {}", gif);

        ModelAndView mav = new ModelAndView("gifs/add");
        mav.addObject("gif", gif);
        return mav;
    }
}
