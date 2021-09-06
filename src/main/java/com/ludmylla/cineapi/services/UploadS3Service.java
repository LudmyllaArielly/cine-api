package com.ludmylla.cineapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Random;

@Service
public class UploadS3Service {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.size}")
    private Integer size;

    @Value("${img.prefix}")
    private String prefix;

    public URI uploadStoryPicture(MultipartFile file) {
        try {
            BufferedImage jpgImage = imageService.getJpaImageFromFile(file);
            jpgImage = imageService.cropSquare(jpgImage);
            jpgImage = imageService.resize(jpgImage, size);
            String fileName = prefix + getNumberRandom() + ".jpg";
            return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
        }catch (FilerException e){
           throw new IllegalArgumentException("Unable to load image");
        }
    }

    private Long getNumberRandom(){
        Random random = new Random();
        Long number = random.nextLong() * 1000;
        return number;
    }

}
