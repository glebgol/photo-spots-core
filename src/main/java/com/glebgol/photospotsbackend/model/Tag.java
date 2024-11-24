package com.glebgol.photospotsbackend.model;

import lombok.Data;

@Data
public class Tag {

    private Long id;
    private double longitude;
    private double latitude;
    private int userId;
}
