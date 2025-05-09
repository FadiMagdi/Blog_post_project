package com.FadiMagdi.Blog.post.project.controllers;

import com.FadiMagdi.Blog.post.project.Dtos.PostDto;
import com.FadiMagdi.Blog.post.project.Mappers.PostMapper;
import com.FadiMagdi.Blog.post.project.Services.PostService;
import com.FadiMagdi.Blog.post.project.Services.UserService;
import com.FadiMagdi.Blog.post.project.domain.Entities.Post;
import com.FadiMagdi.Blog.post.project.domain.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static java.util.stream.StreamSupport.stream;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

private final PostService postService;

private final PostMapper postMapper;

private final UserService userService;

@GetMapping
    public ResponseEntity<List<PostDto>> getPosts(
@RequestParam(required = false) UUID categoryID,
@RequestParam(required = false) UUID tagID
){

List<Post> retrievedPosts = this.postService.getAllPosts(categoryID,tagID);

List<PostDto> postDtos= retrievedPosts.stream().map(post->this.postMapper.toDto(post)).toList();

return ResponseEntity.ok(postDtos);
}


@GetMapping(path="/draft")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userID){

    User loggedInUser = this.userService.getUserById(userID);

List<Post> draftPosts = this.postService.getDraftPosts(loggedInUser);

List<PostDto> draftdtos = draftPosts.stream().map(postMapper::toDto).toList();

return ResponseEntity.ok(draftdtos);


}


}
