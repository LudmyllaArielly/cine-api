package com.ludmylla.cineapi.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.bucketName}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) throws FilerException {
        try{
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return null;

        }catch (IOException e){
            throw  new FilerException("Error of IO: "+ e.getMessage());
        }
    }

    public URI uploadFile(InputStream is, String fileName, String contentType) throws FilerException {
        try{
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            amazonS3.putObject(bucketName, fileName, is, meta);
            return amazonS3.getUrl(bucketName, fileName).toURI();
        }catch (URISyntaxException e){
            throw new FilerException("Error converting URL to URI");
        }
    }

}
