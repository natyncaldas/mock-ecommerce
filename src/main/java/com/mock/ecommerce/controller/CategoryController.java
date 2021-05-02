package com.mock.ecommerce.controller;

import com.mock.ecommerce.exception.ResourceNotFoundException;
import com.mock.ecommerce.model.Category;
import com.mock.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    ResponseEntity<Category> getCategoryById(@PathVariable("id") String id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found"));

        return ResponseEntity.ok(category);
    }

    @PostMapping("/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Category> updateCategory(@PathVariable("id") String id, @Valid @RequestBody Category category) throws ResourceNotFoundException {
        Category _category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found"));
        _category.setName(category.getName());
        _category.setIcon(category.getIcon());
        final Category updatedCategory = categoryRepository.save(_category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteCategoryById(@PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        Category _category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found"));

        categoryRepository.delete(_category);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
