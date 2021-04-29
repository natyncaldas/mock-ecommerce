package com.mock.ecommerce.repository;

import com.mock.ecommerce.model.Annoucement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnnoucementRepository extends MongoRepository<Annoucement, String> {
}

