package com.glebgol.photospotsbackend.services.impl;

import com.glebgol.photospotsbackend.services.ImageUrlResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class DefaultImageUrlResolver implements ImageUrlResolver {

    @Override
    public String getImageUrl(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/images/" + imageName;
    }
}
