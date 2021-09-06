package com.ludmylla.cineapi.resource;

import com.ludmylla.cineapi.services.UploadS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uploads")
public class UploadS3Resource {

    @Autowired
    private UploadS3Service uploadS3Service;

    @PostMapping("/{file}")
    public ResponseEntity<?> uploadFile(@RequestPart(name = "file") MultipartFile file){
        return ResponseEntity.ok(uploadS3Service.uploadStoryPicture(file));
    }
}
