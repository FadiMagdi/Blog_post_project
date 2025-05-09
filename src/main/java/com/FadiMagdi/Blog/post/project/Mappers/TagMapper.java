package com.FadiMagdi.Blog.post.project.Mappers;


import com.FadiMagdi.Blog.post.project.Dtos.TagDto;
import com.FadiMagdi.Blog.post.project.domain.Entities.Post;
import com.FadiMagdi.Blog.post.project.domain.Entities.Tag;
import com.FadiMagdi.Blog.post.project.domain.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount",source = "posts",qualifiedByName = "CalculatePostCount")
    TagDto toTagResponse(Tag tag);

@Named("calculatePostCount")
default Integer calculatePostCount(Set<Post> posts){

if(posts == null){
    return 0;
}
return (int) posts.stream()
        .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
        .count();


}


}
