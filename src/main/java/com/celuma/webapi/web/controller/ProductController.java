package com.celuma.webapi.web.controller;

import com.celuma.webapi.domain.ProductDTO;
import com.celuma.webapi.domain.ProductDetailDTO;
import com.celuma.webapi.domain.request_models.NewProductRequest;
import com.celuma.webapi.service.ProductService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    private final Cloudinary cloudinary;

    public ProductController(
            ProductService productService,
            @Value("${cloudinary.cloud.name}") String cloudName,
            @Value("${cloudinary.cloud-key}") String apiKey,
            @Value("${cloudinary.cloud-secret}") String apiSecret
            ) {
        this.productService= productService;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
           "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    };

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file")MultipartFile file,
            @RequestParam("id")Integer productId) {
        System.out.println("Filename: " + file.getOriginalFilename());
        System.out.println("Id: " + productId);
      try {
          Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
          String imageUrl = (String) uploadResult.get("secure_url");

          ProductDTO productDTO = new ProductDTO();
          productDTO.setImageUrl(imageUrl);
          productDTO.setProductId(productId);
          productService.uploadImage(productDTO);

          return ResponseEntity.ok("Image uploaded" + imageUrl);
      } catch (IOException e ) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
      }
    };

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
    public ResponseEntity<String> delete(@PathVariable("id") int productId) {

        try {
            productService.delete(productId);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found: " + e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
        return null;
    }

    @PatchMapping("/update")
    public void updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
    }

}
