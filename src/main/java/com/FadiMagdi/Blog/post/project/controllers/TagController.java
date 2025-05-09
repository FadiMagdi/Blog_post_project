package com.FadiMagdi.Blog.post.project.controllers;

import com.FadiMagdi.Blog.post.project.Dtos.CreateTagRequest;
import com.FadiMagdi.Blog.post.project.Dtos.TagDto;
import com.FadiMagdi.Blog.post.project.Mappers.TagMapper;
import com.FadiMagdi.Blog.post.project.Services.TagService;
import com.FadiMagdi.Blog.post.project.domain.Entities.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
private final TagMapper tagMapper;


    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags(){

        List<Tag> tags = tagService.getTags();
List<TagDto> tagRespons =tags.stream().map(tagMapper::toTagResponse).toList();
return ResponseEntity.ok(tagRespons);

    }


    @PostMapping
    public ResponseEntity<List<TagDto>> createTag(@RequestBody CreateTagRequest createTagRequest){

        List<Tag> savedTags = tagService.createTags(createTagRequest.getNames());
List<TagDto> createResponses = savedTags.stream().map(tagMapper::toTagResponse).toList();

return new ResponseEntity<>(
        createResponses,
        HttpStatus.CREATED
);

    }


    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("id") UUID tagID){
        this.tagService.deleteTag(tagID);

        return ResponseEntity.noContent().build();

    }

}
