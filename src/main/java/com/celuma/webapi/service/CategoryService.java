package com.celuma.webapi.service;

import com.celuma.webapi.domain.Category;
import com.celuma.webapi.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return categoryRepository.getById(categoryId);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(int categoryId) {
        categoryRepository.delete(categoryId);
    }

    public void update(Category category) {
        categoryRepository.update(category);
    }

}
