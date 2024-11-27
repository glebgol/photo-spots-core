package com.glebgol.photospotsbackend.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateTagRequest {

    private MultipartFile image;
    private String description;
    private Float longitude;
    private Float latitude;
}
