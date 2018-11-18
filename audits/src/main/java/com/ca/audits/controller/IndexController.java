package com.ca.audits.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ca.audits.model.User;
import com.ca.audits.repo.UserRepository;

@Controller
@CrossOrigin
public class IndexController implements ErrorController {
	
	private static Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String getIndex() {
		/*User user = new User();
		user.setEmailId("ajay@gmail.com");
		user.setPassword(passwordEncoder.encode("ajay"));
		user.setName("ajay datla");
		userRepo.save(user);*/
		return "home";
	}
	
	@GetMapping("/loginpage")
	public String getLoginpage() {
		return "loginpage";
	}
	
	@PostMapping("/home")
	public String getUserHome() {
		log.info("user has logged in");
		return "userhome";
	}
	
	@GetMapping("/error")
	public String postError() {
		return "errorpage";
	}

	@PostMapping("/error")
	public String getError() {
		return "errorpage";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	
}
