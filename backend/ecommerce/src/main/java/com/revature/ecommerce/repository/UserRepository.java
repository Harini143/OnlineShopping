package com.revature.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
}
