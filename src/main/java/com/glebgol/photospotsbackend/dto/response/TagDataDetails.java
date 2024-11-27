package com.glebgol.photospotsbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagDataDetails {

    private Long id;
    private double longitude;
    private double latitude;
    private String description;
    private String imageUri;
}
