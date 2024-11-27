package com.glebgol.photospotsbackend.services.impl;

import com.glebgol.photospotsbackend.services.ImageService;
import io.minio.*;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
public class MinioImageService implements ImageService {

    @PostConstruct
    public void createBucketIfNotExist() {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("images").build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("images").build());
                System.out.println("Bucket 'images' created successfully.");
            } else {
                System.out.println("Bucket 'images' already exists.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error checking or creating bucket", e);
        }
    }

    @Autowired
    MinioClient minioClient;

    @Override
    public String uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("No file uploaded");
        }

        try {
            String uploadedFilename = getFileName();

            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket("images")
                    .object(uploadedFilename)
                    .stream(image.getInputStream(), -1, 10485760)
                    .build());

            return uploadedFilename;
        } catch (Exception e) {
            throw new RuntimeException("File upload error");
        }
    }

    @Override
    public Optional<byte[]> getImage(String name) {
        try (InputStream imageStream = minioClient.getObject(GetObjectArgs.builder()
                .bucket("images").object(name).build())) {
            return Optional.of(imageStream.readAllBytes());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @NotNull
    private static String getFileName() {
        return UUID.randomUUID().toString();
    }
}
