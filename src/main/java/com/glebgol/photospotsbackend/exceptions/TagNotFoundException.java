package com.glebgol.photospotsbackend.exceptions;

public class TagNotFoundException extends RuntimeException {

    private Long tagId;

    public TagNotFoundException(Long id) {
        tagId = id;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
