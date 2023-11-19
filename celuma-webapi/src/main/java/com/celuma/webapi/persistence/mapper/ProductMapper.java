package com.celuma.webapi.persistence.mapper;

import com.celuma.webapi.domain.Product;
import com.celuma.webapi.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "contenido", target = "content"),
            @Mapping(source = "instrucciones", target = "instructions"),
            @Mapping(source = "precauciones", target = "cautions"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categorias", target = "category")

    })
    Product toProduct(Producto producto);

    List<Product> toProducts(List<Producto> productos);

    @InheritInverseConfiguration
    Producto toProducto(Product product);

}
