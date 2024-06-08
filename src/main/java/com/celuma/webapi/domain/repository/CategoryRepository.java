package com.celuma.webapi.domain.repository;

import com.celuma.webapi.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> getAll();

    Optional<Category> getById(int categoryId);

    Category save(Category category);

    void delete(int categoryId);

    void update(Category category);
}
