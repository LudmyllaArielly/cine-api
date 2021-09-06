package com.ludmylla.cineapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.awt.image.BufferedImage;
import java.net.URI;

@Service
public class UploadS3Service {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.size}")
    private Integer size;

    public URI uploadStoryPicture(MultipartFile file) throws FilerException {
        BufferedImage jpgImage = imageService.getJpaImageFromFile(file);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = file.getOriginalFilename() + ".jpg";
        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }

}
