package com.example.gifs.controller;

import com.example.gifs.model.Gif;
import com.example.gifs.service.FileService;
import com.example.gifs.service.GifNotFoundException;
import com.example.gifs.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by marcin on 15.07.17.
 */
@RequestMapping("/files")
@Controller
public class FilesController {

    @Autowired
    private GifService gifService;
    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable("id") Long gifId) throws GifNotFoundException{
        Gif gif = gifService.findById(gifId);
        Resource resource = new ByteArrayResource(gif.getImage());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"image.jpg\"")
                .body(resource);
    }
}
