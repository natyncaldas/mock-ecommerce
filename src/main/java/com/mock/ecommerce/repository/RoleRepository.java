package com.mock.ecommerce.repository;

import com.mock.ecommerce.model.ERole;
import com.mock.ecommerce.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(ERole name);
}