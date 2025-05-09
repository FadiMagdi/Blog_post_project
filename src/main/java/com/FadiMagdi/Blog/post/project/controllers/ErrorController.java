package com.FadiMagdi.Blog.post.project.controllers;

import com.FadiMagdi.Blog.post.project.Dtos.ApiErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {
@ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex){
log.error("Caught Exception",ex);

ApiErrorResponse apiError = ApiErrorResponse.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("An unexpected error occured")
        .build();

return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);


    }

@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentExcpetion(IllegalArgumentException ex){
ApiErrorResponse apierror = ApiErrorResponse.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message("Bad Request")
        .build();

return new ResponseEntity<>(apierror,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalException(IllegalStateException ex){
        ApiErrorResponse apierror = ApiErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message("Illegal State")
                .build();

        return new ResponseEntity<>(apierror,HttpStatus.CONFLICT);

    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialExcpetion(BadCredentialsException ex){
        ApiErrorResponse apierror = ApiErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Unauthorized")
                .build();

        return new ResponseEntity<>(apierror,HttpStatus.UNAUTHORIZED);

    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> EntityNotFoundException(EntityNotFoundException ex){
        ApiErrorResponse apierror = ApiErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Not Found")
                .build();

        return new ResponseEntity<>(apierror,HttpStatus.NOT_FOUND);

    }


}
