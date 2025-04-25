package com.celuma.webapi.domain.repository;

import com.celuma.webapi.domain.ProductDTO;
import com.celuma.webapi.domain.ProductDetailDTO;
import com.celuma.webapi.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<ProductDTO> getAll();

    Optional<ProductDetailDTO> getProduct(int productId);

    void save(Producto producto);

    void delete(int productId);

    void updateProduct(ProductDTO productDTO);

    void uploadImage(ProductDTO productDTO);
}
