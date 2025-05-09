package com.FadiMagdi.Blog.post.project.Services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {


    UserDetails autherticate(String email,String password);

    String generateToken(UserDetails userDetails);

    UserDetails validateToken(String token);

}
