package com.mock.ecommerce.repository;

import com.mock.ecommerce.model.Announcement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnnouncementRepository extends MongoRepository<Announcement, String> {
}

