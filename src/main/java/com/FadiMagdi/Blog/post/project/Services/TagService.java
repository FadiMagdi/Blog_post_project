package com.FadiMagdi.Blog.post.project.Services;

import com.FadiMagdi.Blog.post.project.domain.Entities.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<Tag> getTags();

    List<Tag> createTags(Set<String> names);

    Tag getTagById(UUID id);

    void deleteTag(UUID id);
}
