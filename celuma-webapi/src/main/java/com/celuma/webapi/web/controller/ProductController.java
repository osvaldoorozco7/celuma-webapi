package com.celuma.webapi.web.controller;

import com.celuma.webapi.domain.Product;
import com.celuma.webapi.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    private String home() {
        return "Home page";
    }

    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAll();
    };

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Integer productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") int productId) {
        return productService.delete(productId);
    }

}
