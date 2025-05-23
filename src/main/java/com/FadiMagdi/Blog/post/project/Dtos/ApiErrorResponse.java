package com.FadiMagdi.Blog.post.project.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
private String message;

private int status;

private List<FieldError> errors ;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public static class FieldError{

    private String field;

    private String message;


}

}
