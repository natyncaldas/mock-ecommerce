package com.mock.ecommerce.repository;

import com.mock.ecommerce.model.SellerRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SellerRequestRepository extends MongoRepository<SellerRequest, String> {
    SellerRequest findByUserId(String userId);
}
