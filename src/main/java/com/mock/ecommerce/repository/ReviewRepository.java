package com.mock.ecommerce.repository;

import com.mock.ecommerce.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
}
