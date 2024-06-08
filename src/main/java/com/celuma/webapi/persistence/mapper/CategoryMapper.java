package com.celuma.webapi.persistence.mapper;

import com.celuma.webapi.domain.Category;
import com.celuma.webapi.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "categoriaId", target = "categoryId"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria);

    List<Category> toCategories(List<Categoria> categoria);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);

}
