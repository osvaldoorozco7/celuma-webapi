package com.celuma.webapi.web.controller;

import com.celuma.webapi.domain.ProductDTO;
import com.celuma.webapi.domain.ProductDetailDTO;
import com.celuma.webapi.domain.UserDTO;
import com.celuma.webapi.domain.request_models.NewProductRequest;
import com.celuma.webapi.persistence.entity.Producto;
import com.celuma.webapi.service.ProductService;

import jakarta.persistence.EntityExistsException;
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

    @PostMapping()
    public ResponseEntity<String> newProduct(@Valid @RequestBody NewProductRequest request) {
        try {
            ProductDTO productDTO = new ProductDTO();

            productDTO.setName(request.getName());
            productDTO.setContent(request.getContent());
            productDTO.setCategoryId(request.getCategory());
            productDTO.setCautions(Optional.ofNullable(request.getCautions()).orElse(""));
            productDTO.setInstructions(Optional.ofNullable(request.getInstructions()).orElse(""));

            productService.save(productDTO);
            return ResponseEntity.ok("Product successfully created");
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return  new ResponseEntity<>("There was a problem" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
