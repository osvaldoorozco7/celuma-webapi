package com.celuma.webapi.persistence;

import com.celuma.webapi.domain.Category;
import com.celuma.webapi.domain.repository.CategoryRepository;
import com.celuma.webapi.persistence.crud.CategoriaCrudRepository;
import com.celuma.webapi.persistence.entity.Categoria;
import com.celuma.webapi.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository implements CategoryRepository {

    @Autowired
    private CategoriaCrudRepository categoriaCrudRepository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public List<Category> getAll() {
        List<Categoria> categorias = (List<Categoria>) categoriaCrudRepository.findAll();
        return mapper.toCategories(categorias);
    }

    @Override
    public Optional<Category> getById(int categoryId) {
        return categoriaCrudRepository.findById(categoryId).map(categoria -> mapper.toCategory(categoria));
    }

    @Override
    public Category save(Category category) {
        Categoria categoria = mapper.toCategoria(category);
        return mapper.toCategory(categoriaCrudRepository.save(categoria));
    }

    @Override
    public void delete(int categoryId) {
        categoriaCrudRepository.deleteById(categoryId);
    }

    public void update(Category category) {
        Categoria categoria = mapper.toCategoria(category);
        String name = categoria.getDescripcion();
        Integer id = categoria.getCategoriaId();
        categoriaCrudRepository.updateCategory(name, id);
    }
}
