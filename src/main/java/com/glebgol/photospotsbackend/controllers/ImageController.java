package com.glebgol.photospotsbackend.controllers;

import com.glebgol.photospotsbackend.services.ImageService;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageService imageService;

    @GetMapping(value = "/{name}", produces = "image/jpeg")
    public byte[] getImage(@PathVariable String name) {
        return imageService.getImage(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(MultipartFile image) {
        try {
            return ResponseEntity.ok(imageService.uploadImage(image));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
