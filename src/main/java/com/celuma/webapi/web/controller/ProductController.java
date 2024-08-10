package com.celuma.webapi.web.controller;

import com.celuma.webapi.domain.ProductDTO;
import com.celuma.webapi.domain.ProductDetailDTO;
import com.celuma.webapi.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
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

    @PostMapping("/save")
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int productId) {
        productService.delete(productId);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody ProductDTO productDTO) {
        System.out.println("Este es el contenido: " + productDTO.getContent());
        System.out.println("Esta es la categoría: " + productDTO.getCategoryId().getClass());
        System.out.println("Este es el Id: " + productDTO.getProductId().getClass());
        productService.updateProduct(productDTO);
    }

}
