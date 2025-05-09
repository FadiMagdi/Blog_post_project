package com.FadiMagdi.Blog.post.project.domain.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name="categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {

@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

@Column(nullable = false,unique = true)
    private String name;

@OneToMany(mappedBy = "category")
private List<Post> posts = new ArrayList<Post>();




    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn (name="post_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")


    )
private Set<Tag> tags = new HashSet<Tag>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

