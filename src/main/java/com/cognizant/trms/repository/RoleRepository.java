package com.cognizant.trms.repository;

import com.cognizant.trms.model.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
    Author: Aravindan Dandapani
*/
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(String role);
}
