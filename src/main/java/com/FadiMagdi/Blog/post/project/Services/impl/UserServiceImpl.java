package com.FadiMagdi.Blog.post.project.Services.impl;

import com.FadiMagdi.Blog.post.project.Repositories.UserRepository;
import com.FadiMagdi.Blog.post.project.Services.UserService;
import com.FadiMagdi.Blog.post.project.domain.Entities.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;


    @Override
    public User getUserById(UUID id) {
        return this.userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found with id:"+id));


    }
}
