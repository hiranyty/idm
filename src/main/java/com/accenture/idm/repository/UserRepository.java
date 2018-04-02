package com.accenture.idm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accenture.idm.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	User findByUserName(String userName);
	User findByUserNameAndPassword(String userName,String password);
	User findByEmail(String email);	
}



