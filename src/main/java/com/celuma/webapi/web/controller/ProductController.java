package com.celuma.webapi.web.controller;

import com.celuma.webapi.domain.ProductDTO;
import com.celuma.webapi.domain.ProductDetailDTO;
import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.request_models.NewProductRequest;
import com.celuma.webapi.persistence.entity.Producto;
import com.celuma.webapi.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    private String home() {
        return "Home page";
    }

    @GetMapping("/all")
    public List<ProductDTO> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<ProductDetailDTO> getProduct(@PathVariable("id") Integer productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/new-product")
    public ResponseEntity<String> newProduct(@Valid @RequestBody NewProductRequest request) {
        productService.save(request);
        return ResponseEntity.ok("Product successfully created");
    };

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int productId) {
        productService.delete(productId);
    }

    @PatchMapping("/update")
    public void updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
    }

}
