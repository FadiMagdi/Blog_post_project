package com.FadiMagdi.Blog.post.project.controllers;


import com.FadiMagdi.Blog.post.project.Dtos.CategoryDto;
import com.FadiMagdi.Blog.post.project.Dtos.CreateCategoryRequest;
import com.FadiMagdi.Blog.post.project.Mappers.CategoryMapper;
import com.FadiMagdi.Blog.post.project.Services.CategoryService;
import com.FadiMagdi.Blog.post.project.domain.Entities.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listcategories(){
        List<CategoryDto> categories=this.categoryService.listCategories()
                .stream().map(categoryMapper::toDto).toList();


                return ResponseEntity.ok(categories);




    }


    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
            ){
Category categoryToCreate = this.categoryMapper.toEntity(createCategoryRequest);

Category savedCategory = this.categoryService.createCategory(categoryToCreate);


return new ResponseEntity<>(

        categoryMapper.toDto(savedCategory),
        HttpStatus.CREATED

);

    }



@DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
this.categoryService.deleteCategory(id);

return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
