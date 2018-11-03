package com.ca.audits.repo;

import org.springframework.data.repository.CrudRepository;

import com.ca.audits.model.User;
import java.lang.String;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByEmailId(String emailid);

}
