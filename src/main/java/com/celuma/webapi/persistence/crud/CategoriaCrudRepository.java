package com.celuma.webapi.persistence.crud;

import com.celuma.webapi.persistence.entity.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE categories SET description = ?1 WHERE category_id = ?2", nativeQuery = true)
    void updateCategory(String name, Integer id);
}
