package com.FadiMagdi.Blog.post.project.Mappers;

import com.FadiMagdi.Blog.post.project.Dtos.CategoryDto;
import com.FadiMagdi.Blog.post.project.Dtos.CreateCategoryRequest;
import com.FadiMagdi.Blog.post.project.domain.Entities.Category;
import com.FadiMagdi.Blog.post.project.domain.Entities.Post;
import com.FadiMagdi.Blog.post.project.domain.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

@Mapping(target="postCount",source="posts",qualifiedByName ="calculatePostCount" )
    CategoryDto toDto(Category category);

Category toEntity(CreateCategoryRequest createCategoryRequest);

@Named("calculatePostCount")
default long calculatePostCount(List<Post>  posts){
    if(posts == null){
        return 0;
    }

    return posts.stream().filter(post-> PostStatus.PUBLISHED.equals(post.getStatus())).count();

}

}
