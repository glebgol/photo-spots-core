package com.glebgol.photospotsbackend.services.impl;

import com.glebgol.photospotsbackend.dto.request.CreateTagRequest;
import com.glebgol.photospotsbackend.dto.response.TagDataDetails;
import com.glebgol.photospotsbackend.mapper.TagMapper;
import com.glebgol.photospotsbackend.model.Tag;
import com.glebgol.photospotsbackend.repository.TagRepository;
import com.glebgol.photospotsbackend.services.ImageService;
import com.glebgol.photospotsbackend.services.ImageUrlResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultTagsServiceTest {

    @InjectMocks
    private DefaultTagsService tagsService;

    @Mock
    private TagRepository tagRepository;
    @Mock
    private ImageService imageService;
    @Mock
    private TagMapper tagMapper;
    @Mock
    private ImageUrlResolver imageUrlResolver;
    @Mock
    private MultipartFile image;

    private static final Long TAG_ID = 1L;
    private static final CreateTagRequest CREATE_TAG_REQUEST = new CreateTagRequest();
    private static final String IMAGE_URL = "image url";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_NAME = "image name";
    private static final Tag TAG = new Tag(TAG_ID, 1f, 1f, DESCRIPTION, IMAGE_URL);
    private static final TagDataDetails TAG_DATA_DETAILS =
            new TagDataDetails(TAG_ID, 1f, 1f, DESCRIPTION, IMAGE_URL);
    private static final List<Tag> TAGS = List.of(TAG);

    @Test
    public void shouldGetAllTags() {
        when(tagRepository.findAll()).thenReturn(TAGS);

        tagsService.getTags();

        verify(tagRepository).findAll();
    }

    @Test
    public void shouldGetTagDetailsById() {
        when(tagRepository.findById(TAG_ID)).thenReturn(Optional.of(TAG));
        when(tagMapper.toTagDataDetails(TAG)).thenReturn(TAG_DATA_DETAILS);

        tagsService.getTag(TAG_ID);

        verify(tagRepository).findById(TAG_ID);
        verify(tagMapper).toTagDataDetails(TAG);
    }

    @Test
    public void shouldCreateTag() {
        CREATE_TAG_REQUEST.setImage(image);
        when(imageUrlResolver.getImageUrl(IMAGE_NAME)).thenReturn(IMAGE_URL);
        when(imageService.uploadImage(image)).thenReturn(IMAGE_NAME);

        tagsService.createTag(CREATE_TAG_REQUEST);

        verify(tagRepository).save(any());
    }
}
