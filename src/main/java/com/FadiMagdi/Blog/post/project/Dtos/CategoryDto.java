package com.FadiMagdi.Blog.post.project.Dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto {

private UUID id;

private String name;

private long postCount;



}
