package com.glebgol.photospotsbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateTagRequest {

    @NotNull
    private MultipartFile image;
    @NotNull
    private String description;
    @NotNull
    private Float longitude;
    @NotNull
    private Float latitude;
}
