package com.FadiMagdi.Blog.post.project.Services.impl;

import com.FadiMagdi.Blog.post.project.Repositories.PostRepository;
import com.FadiMagdi.Blog.post.project.Services.PostService;
import com.FadiMagdi.Blog.post.project.Services.TagService;
import com.FadiMagdi.Blog.post.project.Services.CategoryService;
import com.FadiMagdi.Blog.post.project.domain.Entities.Category;
import com.FadiMagdi.Blog.post.project.domain.Entities.Post;
import com.FadiMagdi.Blog.post.project.domain.Entities.Tag;
import com.FadiMagdi.Blog.post.project.domain.Entities.User;
import com.FadiMagdi.Blog.post.project.domain.PostStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryID, UUID tagID) {

        if(categoryID !=null && tagID != null){
            Category targetCategory = this.categoryService.getCategoryById(categoryID);

Tag targetTag = this.tagService.getTagById(tagID);

return this.postRepository.findAllByStatusAndCategoryAndTagsContaining(

  PostStatus.PUBLISHED,
  targetCategory,
  targetTag


);
        }

if(categoryID !=null){
    Category targetCategory = this.categoryService.getCategoryById(categoryID);

    return this.postRepository.findAllByStatusAndCategory(

      PostStatus.PUBLISHED,
      targetCategory
    );

    if(tagID !=null){
        Tag targetTag =this.tagService.getTagById(tagID);

        return this.postRepository.findAllByStatusAndTag(
               PostStatus.PUBLISHED,
               targetTag
        );

    }


return this.postRepository.findAllByStatus(PostStatus.PUBLISHED);

}

    }

    @Override
    public List<Post> getDraftPosts(User user) {



        return this.postRepository.findAllByAuthorAndStatus(user,PostStatus.DRAFT);
    }
}
