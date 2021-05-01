package com.mock.ecommerce.controller;

import com.mock.ecommerce.exception.ResourceNotFoundException;
import com.mock.ecommerce.model.Product;
import com.mock.ecommerce.model.User;
import com.mock.ecommerce.repository.ProductRepository;
import com.mock.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @PutMapping("/users/{id}/cart")
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN') and #id == authentication.principal.id")
    public ResponseEntity<User> editCart(@PathVariable("id") String id, @RequestParam String productId, @RequestParam String operation) throws ResourceNotFoundException {
        User _user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));

        if (operation.equals("add")){
             _user.getCart().add(product);
        }
        if(operation.equals("delete")){
            _user.getCart().removeIf(p -> p.getId().equals(productId));
        }
        if(operation.equals("clear")){
            _user.getCart().clear();
        }

        final User updatedUser = userRepository.save(_user);
        return ResponseEntity.ok(updatedUser);
    }


}
