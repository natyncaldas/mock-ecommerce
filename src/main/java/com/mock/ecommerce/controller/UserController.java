package com.mock.ecommerce.controller;

import com.mock.ecommerce.exception.ResourceNotFoundException;
import com.mock.ecommerce.model.User;
import com.mock.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("(hasAnyRole('USER', 'MODERATOR') and #id == authentication.principal.id) or hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) throws ResourceNotFoundException {
        User _user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found for this ID"));

        return ResponseEntity.ok(_user);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN') and #id == authentication.principal.id")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) throws ResourceNotFoundException {
        User _user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found for this ID"));

        _user.setUsername(user.getUsername());
        _user.setEmail(user.getEmail());
        _user.setPassword(encoder.encode(user.getPassword()));
        _user.setAddress(user.getAddress());
        _user.setFirstName(user.getFirstName());
        _user.setLastName(user.getLastName());
        final User updatedUser = userRepository.save(_user);
        return ResponseEntity.ok(updatedUser);
    }
}
