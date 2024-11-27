package com.glebgol.photospotsbackend.controllers;

import com.glebgol.photospotsbackend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/images")
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
