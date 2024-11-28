package com.glebgol.photospotsbackend.services.impl;

import com.glebgol.photospotsbackend.dto.request.CreateTagRequest;
import com.glebgol.photospotsbackend.dto.response.TagData;
import com.glebgol.photospotsbackend.dto.response.TagDataDetails;
import com.glebgol.photospotsbackend.exceptions.TagNotFoundException;
import com.glebgol.photospotsbackend.mapper.TagMapper;
import com.glebgol.photospotsbackend.model.Tag;
import com.glebgol.photospotsbackend.repository.TagRepository;
import com.glebgol.photospotsbackend.services.ImageService;
import com.glebgol.photospotsbackend.services.ImageUrlResolver;
import com.glebgol.photospotsbackend.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultTagsService implements TagsService {
    private final TagRepository tagRepository;
    private final ImageService imageService;
    private final TagMapper tagMapper;
    private final ImageUrlResolver imageUrlResolver;

    @Override
    public List<TagData> getTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::toTagData)
                .collect(Collectors.toList());
    }

    @Override
    public TagDataDetails getTag(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::toTagDataDetails)
                .orElseThrow(() -> new TagNotFoundException(id));
    }

    @Override
    @Transactional
    public Tag createTag(CreateTagRequest createTagRequest) {
        String imageName = imageService.uploadImage(createTagRequest.getImage());

        return tagRepository.save(getNewTag(createTagRequest, imageUrlResolver.getImageUrl(imageName)));
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    private static Tag getNewTag(CreateTagRequest createTagRequest, String imageUri) {
        Tag newTag = new Tag();
        newTag.setImageUri(imageUri);
        newTag.setLatitude(createTagRequest.getLatitude());
        newTag.setLongitude(createTagRequest.getLongitude());
        newTag.setDescription(createTagRequest.getDescription());
        return newTag;
    }
}
