package com.glebgol.photospotsbackend.mapper;

import com.glebgol.photospotsbackend.dto.response.TagData;
import com.glebgol.photospotsbackend.dto.response.TagDataDetails;
import com.glebgol.photospotsbackend.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagData toTagData(Tag tag) {
        if (tag == null) {
            return null;
        }
        return new TagData(tag.getId(), tag.getLongitude(), tag.getLatitude());
    }

    public TagDataDetails toTagDataDetails(Tag tag) {
        if (tag == null) {
            return null;
        }
        return new TagDataDetails(tag.getId(), tag.getLongitude(), tag.getLatitude(),
                tag.getDescription(), tag.getImageUri());
    }

}
