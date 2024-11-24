package com.glebgol.photospotsbackend.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ImageService {
    String uploadImage(MultipartFile image);
    Optional<byte[]> getImage(String name);
}
