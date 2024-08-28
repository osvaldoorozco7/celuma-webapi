package com.celuma.webapi.web.controller;

import com.celuma.webapi.domain.Category;
import com.celuma.webapi.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategory(@PathVariable("id")Integer idCategory) {
        return categoryService.getCategory(idCategory);
    }

    @PostMapping("/save")
    public Category save(@RequestBody Category category) {
        System.out.println(category.getDescription());
        return categoryService.save(category);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int categoryId) {
        System.out.println(categoryId);
        categoryService.delete(categoryId);
    }

    @PutMapping("/update")
    public void updateCategory(@RequestBody Category category) {
        categoryService.update(category);
    }

}
