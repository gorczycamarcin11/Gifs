package com.example.gifs.controller;

import com.example.gifs.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FileService fileService;

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable("filename") String fileName){
        Resource fileAsResource = fileService.getFileAsResource(fileName);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+fileAsResource.getFilename()+"\"")
                .body(fileAsResource);
    }
}
