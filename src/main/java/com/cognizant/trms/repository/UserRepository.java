package com.cognizant.trms.repository;

import com.cognizant.trms.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
    Author: Aravindan Dandapani
*/
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
