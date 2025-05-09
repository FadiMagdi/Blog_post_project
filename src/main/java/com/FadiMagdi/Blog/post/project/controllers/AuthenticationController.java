package com.FadiMagdi.Blog.post.project.controllers;

import com.FadiMagdi.Blog.post.project.Dtos.AuthResponse;
import com.FadiMagdi.Blog.post.project.Dtos.LoginRequest;
import com.FadiMagdi.Blog.post.project.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthenticationController {


    public final AuthenticationService authenticationService;

 public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
     UserDetails userDetails = authenticationService.autherticate(
             loginRequest.getEmail(),
             loginRequest.getPassword()
     );

     String tokenValue = this.authenticationService.generateToken(userDetails);

    AuthResponse authResponse= AuthResponse.builder()
             .token(tokenValue)
             .expiresIn(86400) // token valid for 24 hours
             .build();


    return ResponseEntity.ok(authResponse);
 }


}
