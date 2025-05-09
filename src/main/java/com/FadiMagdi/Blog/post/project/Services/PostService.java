package com.FadiMagdi.Blog.post.project.Services;

import com.FadiMagdi.Blog.post.project.domain.Entities.Post;
import com.FadiMagdi.Blog.post.project.domain.Entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    public List<Post> getAllPosts(UUID categoryID,UUID tagID);
    public List<Post> getDraftPosts(User user);

}
