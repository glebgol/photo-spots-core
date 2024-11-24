package com.glebgol.photospotsbackend.controllers;

import com.glebgol.photospotsbackend.model.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    @GetMapping
    public List<Tag> getTags() {
        return List.of(
                new Tag(1L, 34.567, -12.345),
                new Tag(2L, -78.123, 45.678),
                new Tag(3L, 10.456, -22.789),
                new Tag(4L, 10.0, 20.0),
                new Tag(5L, -10.0, 50.0),
                new Tag(6L, 75.5, -10.1),
                new Tag(7L, 0.0, 0.0),
                new Tag(8L, -45.0, 9.0),
                new Tag(9L, 10.0, -9.0),
                new Tag(10L, 50.0, 25.0)
        );
    }
}
