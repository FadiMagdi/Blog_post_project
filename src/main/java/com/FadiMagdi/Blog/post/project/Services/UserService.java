package com.FadiMagdi.Blog.post.project.Services;

import com.FadiMagdi.Blog.post.project.domain.Entities.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);

}
