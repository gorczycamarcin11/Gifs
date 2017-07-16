package com.example.gifs.controller;

import com.example.gifs.model.Gif;
import com.example.gifs.service.GifNotFoundException;
import com.example.gifs.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by marcin on 14.07.17.
 */
@RequestMapping("/gif")
@Controller
public class GifDetailsController {

    @Autowired
    private GifService gifServiceImpl;


    @GetMapping("/{id}")
    public ModelAndView getDetails(@PathVariable("id") Long id) {

        if (id == null || id < 0) {
            return redirect();
        } else {
            try {
                Gif gif = gifServiceImpl.findById(id);
                ModelAndView mav = new ModelAndView("gifs/details");
                mav.addObject("gif", gif);
                return mav;
            } catch (GifNotFoundException e) {
                return redirect();
            }
        }
    }

    @GetMapping("random")
    public ModelAndView findRandom() throws GifNotFoundException{
        try {
            Gif gif = gifServiceImpl.findRandom();
            ModelAndView mav = new ModelAndView("gifs/details");
            mav.addObject("gif", gif);
            return mav;
        } catch (GifNotFoundException e) {
            return redirect();
        }
    }

    private ModelAndView redirect() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }

}
