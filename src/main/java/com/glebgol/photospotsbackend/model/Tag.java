package com.glebgol.photospotsbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tag {

    private Long id;
    private double longitude;
    private double latitude;
}
