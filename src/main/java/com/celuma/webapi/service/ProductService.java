package com.celuma.webapi.service;

import com.celuma.webapi.domain.ProductDTO;
import com.celuma.webapi.domain.ProductDetailDTO;
import com.celuma.webapi.domain.repository.ProductRepository;
import com.celuma.webapi.domain.request_models.NewProductRequest;
import com.celuma.webapi.persistence.entity.Producto;
import com.celuma.webapi.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void save(NewProductRequest request) {
        Producto producto = new Producto();

        producto.setNombre(request.getName());
        producto.setContenido(request.getContent());
        producto.setIdCategoria(request.getCategory());

        producto.setInstrucciones(request.getInstructions() != null ? request.getInstructions() : "");
        producto.setPrecauciones(request.getCautions() != null ? request.getCautions() : " ");

        productRepository.save(mapper.toProduct(producto));
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
