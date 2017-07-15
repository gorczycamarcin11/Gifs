package com.example.gifs.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

/**
 * Created by marcin on 15.07.17.
 */
@Service
public class FileServiceImpl implements FileService {

    private static final String UPLOAD_PATH = "upload";

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    private void createDirectory() throws IOException {
        Path uploadPath = Paths.get(UPLOAD_PATH);
        if (!Files.exists(uploadPath)) {
            Files.createDirectory(uploadPath);
        }
    }

    @Override
    public String store(MultipartFile file) throws IOException, FileServiceException {
        this.createDirectory();

        if (file.isEmpty()) {
            throw new MultipartException("File is empty");
        }
        if (!isImage(file)) {
            throw new MultipartException("File is in forbidden format");
        }

        InputStream is = file.getInputStream();
        Path uploadLocation = Paths.get(UPLOAD_PATH);
        long timeStamp = Instant.now().getEpochSecond();
        String newFileName = timeStamp + " - " + file.getOriginalFilename();
        Path targetPath = uploadLocation.resolve(newFileName);
        long bytesWritten = Files.copy(is, targetPath);

        if (bytesWritten <= 0) {
            throw new FileServiceException("Couldn't copy file");
        }
        return newFileName;
    }

    @Override
    public Resource getFileAsResource(String filePath) {
        try {
            Path uploadPath = Paths.get(UPLOAD_PATH);
            Path targetFilePath = uploadPath.resolve(filePath);
            Resource resource = new UrlResource(targetFilePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
        }
        return null;
    }
}

