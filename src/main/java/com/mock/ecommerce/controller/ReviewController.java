package com.mock.ecommerce.controller;

import com.mock.ecommerce.exception.ResourceNotFoundException;
import com.mock.ecommerce.model.Review;
import com.mock.ecommerce.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/products/{id}/reviews")
    List<Review> getReviewsByProductId(@PathVariable("id") String productId){
        return reviewRepository.findByProductId(productId);
    }

    @PostMapping("/products/{id}/reviews")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN') and #review.username == authentication.principal.username and #productId == #review.productId")
    public Review createReview(@Valid @RequestBody Review review, @PathVariable("id") String productId){
        return reviewRepository.save(review);
    }

    @DeleteMapping("/users/{username}/reviews/{id}")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN') and #username == authentication.principal.username")
    public Map<String, Boolean> deleteReviewById(@PathVariable(value = "id") String id, @PathVariable("username") String username)
            throws ResourceNotFoundException {
        Review _review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        if(!username.equals(_review.getUsername())){
            throw new AccessDeniedException("Access Denied");
        }
        reviewRepository.delete(_review);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
