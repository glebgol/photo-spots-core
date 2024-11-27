package com.glebgol.photospotsbackend.controllers;

import com.glebgol.photospotsbackend.dto.request.CreateTagRequest;
import com.glebgol.photospotsbackend.dto.response.TagData;
import com.glebgol.photospotsbackend.dto.response.TagDataDetails;
import com.glebgol.photospotsbackend.model.Tag;
import com.glebgol.photospotsbackend.services.TagsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    private final TagsService tagsService;

    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping
    public List<TagData> getTags() {
        return tagsService.getTags();
    }

    @GetMapping("/{id}")
    public TagDataDetails getTag(@PathVariable Long id) {
        return tagsService.getTag(id);
    }

    @PostMapping
    public Tag createTag(@ModelAttribute CreateTagRequest createTagRequest) {
        return tagsService.createTag(createTagRequest);
    }
}
