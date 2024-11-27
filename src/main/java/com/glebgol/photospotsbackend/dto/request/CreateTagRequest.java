package com.glebgol.photospotsbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateTagRequest {

    @NotNull(message = "image should be not null")
    private MultipartFile image;
    @NotNull(message = "description should be not null")
    private String description;
    @NotNull(message = "longitude should be not null")
    private Float longitude;
    @NotNull(message = "latitude should be not null")
    private Float latitude;
}
