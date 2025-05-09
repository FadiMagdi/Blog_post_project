package com.FadiMagdi.Blog.post.project.security;

import com.FadiMagdi.Blog.post.project.Repositories.UserRepository;
import com.FadiMagdi.Blog.post.project.domain.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException("User not found with this email"));

        return new BlogUserDetails(user);
    }
}
