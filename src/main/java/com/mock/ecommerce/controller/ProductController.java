package com.mock.ecommerce.controller;

import com.mock.ecommerce.exception.ResourceNotFoundException;
import com.mock.ecommerce.model.Product;
import com.mock.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class  ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestParam(required = false) String sort){
        sort = (sort == null) ? "":sort;
        return switch (sort) {
            case "ASC" -> productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
            case "DESC" -> productRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
            default -> productRepository.findAll();
        };
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String productId, @RequestParam(required = false) String sort) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return ResponseEntity.ok(product);
    }

    @GetMapping("categories/{id}/products")
    public List<Product> getProductsByCategory(@PathVariable("id") String categoryId, @RequestParam(required = false) String sort){
        sort = (sort == null) ? "":sort;
        return switch (sort) {
            case "ASC" -> productRepository.findByCategoryId(categoryId, Sort.by(Sort.Direction.ASC, "price"));
            case "DESC" -> productRepository.findByCategoryId(categoryId, Sort.by(Sort.Direction.DESC, "price"));
            default -> productRepository.findByCategoryId(categoryId, null);
        };
    }

    @PostMapping("/products")
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN') and #product.sellerUsername == authentication.principal.username")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("(hasRole('MODERATOR') and #product.sellerUsername == authentication.principal.username) or hasRole('ADMIN')")
    public Map<String, Boolean> deleteProductById(@PathVariable(value = "id") String id, Product product)
            throws ResourceNotFoundException {
        product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
