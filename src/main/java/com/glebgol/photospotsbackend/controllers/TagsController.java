package com.glebgol.photospotsbackend.controllers;

import com.glebgol.photospotsbackend.dto.request.CreateTagRequest;
import com.glebgol.photospotsbackend.dto.response.TagData;
import com.glebgol.photospotsbackend.dto.response.TagDataDetails;
import com.glebgol.photospotsbackend.exceptions.TagNotFoundException;
import com.glebgol.photospotsbackend.model.Tag;
import com.glebgol.photospotsbackend.services.TagsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Tag createTag(@Valid @ModelAttribute CreateTagRequest createTagRequest) {
        return tagsService.createTag(createTagRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable Long id) {
        tagsService.deleteTag(id);
        return "Deleted tag " + id;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TagNotFoundException.class)
    public Map<String, String> handleTagNotFoundExceptions(TagNotFoundException e) {
        Map<String, String> message = new HashMap<>();
        message.put("info", String.format("Not found tag with id=%s", e.getTagId()));
        return message;
    }
}
