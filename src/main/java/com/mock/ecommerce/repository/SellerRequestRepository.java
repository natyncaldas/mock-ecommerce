package com.mock.ecommerce.repository;

import com.mock.ecommerce.model.SellerRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRequestRepository extends MongoRepository<SellerRequest, String> {
}
