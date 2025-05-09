package com.FadiMagdi.Blog.post.project.domain.Entities;


import com.FadiMagdi.Blog.post.project.domain.PostStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;

@Column(name="content",nullable = false,columnDefinition = "TEXT")
    private String content;


@Column(nullable = false,name="post_status")
    @Enumerated(EnumType.STRING)
    private PostStatus status;


@Column(nullable = false,name="reading-time")
    private Integer readingTime;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="author_id",nullable = false)
private User author;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="category_id",nullable = false)
private Category category;


@ManyToMany
@JoinTable(
name="post_tags",
        joinColumns=@JoinColumn(name="post_id"),
        inverseJoinColumns = @JoinColumn(name="tag_id")
)
private Set<Tag> tags = new HashSet<Tag>();

@Column(nullable = false,name="creation_date")
    private LocalDateTime createAt;

@Column(nullable = false,name="update_date")
private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(content, post.content) && status == post.status && Objects.equals(readingTime, post.readingTime) && Objects.equals(createAt, post.createAt) && Objects.equals(updatedAt, post.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status, readingTime, createAt, updatedAt);
    }

    @PrePersist
        protected void onCreate(){

        LocalDateTime now = LocalDateTime.now();

        this.createAt = now;
        this.updatedAt = now;



    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();

    }
}
