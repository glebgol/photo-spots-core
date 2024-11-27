package com.glebgol.photospotsbackend.services;

import com.glebgol.photospotsbackend.dto.request.CreateTagRequest;
import com.glebgol.photospotsbackend.dto.response.TagData;
import com.glebgol.photospotsbackend.dto.response.TagDataDetails;
import com.glebgol.photospotsbackend.model.Tag;

import java.util.List;

public interface TagsService {
    List<TagData> getTags();
    TagDataDetails getTag(Long id);
    Tag createTag(CreateTagRequest createTagRequest);
}
