package com.FadiMagdi.Blog.post.project.Mappers;

import com.FadiMagdi.Blog.post.project.Dtos.PostDto;
import com.FadiMagdi.Blog.post.project.domain.Entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author",source="author")
    @Mapping(target = "category",source="category")
    @Mapping(target = "tags",source="tags")
    PostDto toDto(Post post);
}
