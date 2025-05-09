package com.FadiMagdi.Blog.post.project.config;

import com.FadiMagdi.Blog.post.project.Repositories.UserRepository;
import com.FadiMagdi.Blog.post.project.Services.AuthenticationService;
import com.FadiMagdi.Blog.post.project.domain.Entities.User;
import com.FadiMagdi.Blog.post.project.security.BlogUserDetailsService;
import com.FadiMagdi.Blog.post.project.security.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Bean

    public JWTAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService){

        return new JWTAuthenticationFilter(authenticationService);
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        BlogUserDetailsService blogUserDetailsService = new BlogUserDetailsService(userRepository);

         String email="user@test.com";
         userRepository.findByEmail(email).orElseGet(
                 ()->{
                     User newUser = User.builder()
                             .name("Test User")
                             .email(email)
                             .password(passwordEncoder().encode("password"))
                             .build();

                     return userRepository.save(newUser);
                 });
return blogUserDetailsService;
    }


    public SecurityFilterChain securityFilterChain(HttpSecurity http,JWTAuthenticationFilter jwtAuthenticationFilter ) throws Exception{

http
        .authorizeHttpRequests(auth-> auth
                .requestMatchers(HttpMethod.POST,"/api/v1/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/v1/posts/drafts").authenticated()
                .requestMatchers(HttpMethod.GET,"/api/v1/posts/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll()
                .anyRequest().authenticated()
        )
        .csrf(csrf->csrf.disable())
        .sessionManagement(
          session->
          session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
;
return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // to encode password using decrypt

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{

        return config.getAuthenticationManager();

    }


}
