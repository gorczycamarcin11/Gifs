package com.example.gifs.controller;

import com.example.gifs.model.Gif;
import com.example.gifs.service.FileService;
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
import java.nio.file.Path;


/**
 * Created by marcin on 15.07.17.
 */
@RequestMapping("/add")
@Controller
public class AddGifController {

    private static Logger LOG = LoggerFactory.getLogger(AddGifController.class);

    @Autowired
    private FileService fileService;

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

        if (bindingResult.hasErrors()) {

            return addGifFormModelAndView(gif);

        } else {
            try {
                String uploadedFile = fileService.store(file);
                LOG.info("File stored {}", uploadedFile);
                gif.setImagePath(uploadedFile);
                gifService.save(gif);
                return new ModelAndView("redirect:/");
            } catch (IOException | FileServiceException e) {
                e.printStackTrace();
                LOG.error("Error during file store", e);

                bindingResult.addError(new FieldError("gif", "imagePath", "Error sending file"));

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
