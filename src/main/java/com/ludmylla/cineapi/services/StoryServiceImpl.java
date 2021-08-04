package com.ludmylla.cineapi.services;

import com.ludmylla.cineapi.model.User;
import com.ludmylla.cineapi.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.awt.image.BufferedImage;
import java.net.URI;

@Service
public class StoryServiceImpl implements  StoryService{

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix}")
    private String prefix;

    @Value("${img.size}")
    private Integer size;

    @Override
    public URI uploadStoryPicture(MultipartFile file) throws FilerException {
        BufferedImage jpgImage = imageService.getJpaImageFromFile(file);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = file.getName() + ".jpg";
        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
