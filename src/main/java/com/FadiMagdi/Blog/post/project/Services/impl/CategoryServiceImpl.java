package com.FadiMagdi.Blog.post.project.Services.impl;

import com.FadiMagdi.Blog.post.project.Repositories.CategoryRepository;
import com.FadiMagdi.Blog.post.project.Services.CategoryService;
import com.FadiMagdi.Blog.post.project.domain.Entities.Category;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {

if(this.categoryRepository.existsByNameIgnoreCase(category.getName())){
    throw new IllegalArgumentException("Category already exists with name: "+ category.getName());
}


        return this.categoryRepository.save(category) ;
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = this.categoryRepository.findById(id);

        if(category.isPresent()){
            if(!category.get().getPosts().isEmpty()){
                throw new IllegalStateException("category has posts associated with it");
            }

            this.categoryRepository.deleteById(id);
        }



    }

    @Override
    public Category getCategoryById(UUID id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("could not find category with id: "+id));
    }


}
