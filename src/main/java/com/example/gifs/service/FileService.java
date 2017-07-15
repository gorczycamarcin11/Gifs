package com.example.gifs.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by marcin on 15.07.17.
 */
public interface FileService {

    String store(MultipartFile file) throws IOException, FileServiceException;
    Resource getFileAsResource (String filePath);
}
