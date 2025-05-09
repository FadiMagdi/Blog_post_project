package com.FadiMagdi.Blog.post.project.Dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagRequest {

    @NotEmpty(message = "At least one tag name is required")
    @Size(max = 10,message = "maximum name of 10 letters")
    private Set<
            @Pattern(regexp = "^[\\w\\s-]+$" ,message = "Tag name can only contain letters,numbers,spaces")
            String> names;
}
