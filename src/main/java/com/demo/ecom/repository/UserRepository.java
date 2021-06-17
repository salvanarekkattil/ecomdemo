package com.demo.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByUserName(String userName);
	
	UserEntity findByUserNameAndPassword(String userName, String password);
	
}