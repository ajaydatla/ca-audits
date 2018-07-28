package com.ca.audits.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ca.audits.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>  {
	
	public User findByUserId(String userId);

}
