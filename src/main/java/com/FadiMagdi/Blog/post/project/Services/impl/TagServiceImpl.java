package com.FadiMagdi.Blog.post.project.Services.impl;

import com.FadiMagdi.Blog.post.project.Repositories.TagRepository;
import com.FadiMagdi.Blog.post.project.Services.TagService;
import com.FadiMagdi.Blog.post.project.domain.Entities.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl  implements TagService {

    private TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return this.tagRepository.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTags(Set<String> names) {

        List<Tag> existingTags = this.tagRepository.findByNameIn(names);

        Set<String> existingTagName= existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());


        List<Tag> newTags = names.stream()
                .filter(name -> !existingTagName.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .collect(Collectors.toList());

        List<Tag> savedTags = new ArrayList<>();

        if(!newTags.isEmpty()){
            savedTags = this.tagRepository.saveAll(newTags);
        }

        savedTags.addAll(existingTags);


        return savedTags;
    }

    @Override
    public Tag getTagById(UUID id) {



        return this.tagRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("could not find Tag with id: "+id)
        )
                ;
    }

    @Transactional
    @Override
    public void deleteTag(UUID id) {
        tagRepository.findById(id).ifPresent(
tag ->{

if(!tag.getPosts().isEmpty()){
    throw new IllegalStateException("Cannot delete tag with posts");
}

    this.tagRepository.deleteById(id);


}



        );
    }
}
