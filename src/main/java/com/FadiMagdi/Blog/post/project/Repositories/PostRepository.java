package com.FadiMagdi.Blog.post.project.Repositories;

import com.FadiMagdi.Blog.post.project.domain.Entities.Category;
import com.FadiMagdi.Blog.post.project.domain.Entities.Post;
import com.FadiMagdi.Blog.post.project.domain.Entities.Tag;
import com.FadiMagdi.Blog.post.project.domain.Entities.User;
import com.FadiMagdi.Blog.post.project.domain.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{

    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category,Tag tag);

    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);

    List<Post> findAllByStatusAndTag(PostStatus status,Tag tag);

    List<Post> findAllByStatus(PostStatus status);

    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);


}
