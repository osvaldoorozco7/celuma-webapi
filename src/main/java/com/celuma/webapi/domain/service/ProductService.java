package com.celuma.webapi.domain.service;

import com.celuma.webapi.domain.ProductDTO;
import com.celuma.webapi.domain.ProductDetailDTO;
import com.celuma.webapi.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAll(){
        return productRepository.getAll();
    }

    public Optional<ProductDetailDTO> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public ProductDTO save(ProductDTO productDTO) {
        return productRepository.save(productDTO);
    }

    public Boolean delete(int productId) {
        return getProduct(productId).map(product -> {productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

    public void  updateProduct(ProductDTO productDTO) {
        productRepository.updateProduct(productDTO);
    }
}
