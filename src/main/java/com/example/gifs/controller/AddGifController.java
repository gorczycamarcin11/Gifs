package com.example.gifs.controller;

import com.example.gifs.model.Gif;
import com.example.gifs.service.FileServiceException;
import com.example.gifs.service.GifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;


/**
 * Created by marcin on 15.07.17.
 */
@RequestMapping("/add")
@Controller
public class AddGifController {

    private static Logger LOG = LoggerFactory.getLogger(AddGifController.class);

    @Autowired
    private GifService gifService;

    @GetMapping
    public ModelAndView add() {

        return addGifFormModelAndView(new Gif());
    }

    @PostMapping
    public ModelAndView add(@RequestParam("file") MultipartFile file, @Valid Gif gif, BindingResult bindingResult) {

        LOG.info("File received {}", file);
        LOG.info("Gif received {}", gif);

        if (file.isEmpty() || !file.getContentType().startsWith("image/gif")) {
            bindingResult.addError(new FieldError("gif", "image", "File is empty or in forbidden format"));
            return addGifFormModelAndView(gif);
        } else {
            try {
                gif.setImage(file.getBytes());
                gifService.save(gif);
                return new ModelAndView("redirect:/");
            } catch (IOException e) {
                e.printStackTrace();
                LOG.error("Error during file store", e);
                bindingResult.addError(new FieldError("gif", "image", "Error sending file"));
                return addGifFormModelAndView(gif);
            }
        }
    }


    private ModelAndView addGifFormModelAndView(@Valid Gif gif) {
        ModelAndView mav = new ModelAndView("gifs/add");
        mav.addObject("gif", gif);
        return mav;
    }
}
