package com.ca.audits.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ca.audits.model.User;
import com.ca.audits.repo.UserRepository;

@Controller
@CrossOrigin
public class IndexController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String getIndex() {
		System.out.println("inside getIndex");
		/*User user = new User();
		user.setName("ajay datla");
		user.setUserId("ajay@gmail.com");
		user.setPassword(passwordEncoder.encode("ajay"));
		userRepo.insert(user);*/
		return "login";
	}
	
	@PostMapping("/login")
	public String getLogin() {
		System.out.println("inside getLogin");
		return "login";
	}
	
	@PostMapping("/home")
	public String userAuth() {
		System.out.println("inside userAuth");
		
		return "index";
	}
}
