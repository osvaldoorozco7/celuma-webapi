package com.celuma.webapi.service;

import com.celuma.webapi.domain.ProductDTO;
import com.celuma.webapi.domain.ProductDetailDTO;
import com.celuma.webapi.domain.repository.ProductRepository;
import com.celuma.webapi.domain.request_models.NewProductRequest;
import com.celuma.webapi.persistence.entity.Producto;
import com.celuma.webapi.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;



    public List<ProductDTO> getAll(){
        return productRepository.getAll();
    }

    public Optional<ProductDetailDTO> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public void save(ProductDTO productDTO) {
        Producto producto = mapper.toProducto(productDTO);

        try {
            productRepository.save(producto);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error interacting with the database", e);
        }

    };

    public Boolean delete(int productId) {
        return getProduct(productId).map(product -> {productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

    public void  updateProduct(ProductDTO productDTO) {
        productRepository.updateProduct(productDTO);
    }
}
