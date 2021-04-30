package com.mock.ecommerce.repository;

import com.mock.ecommerce.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategoryId(String categoryId, Sort sort);
    @Override
    List<Product> findAll(Sort sort);
}
