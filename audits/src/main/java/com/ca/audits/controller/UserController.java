package com.ca.audits.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ca.audits.model.ClientContacts;
import com.ca.audits.repo.ClientContactsRepository;
import com.ca.audits.repo.UserRepository;
import com.google.gson.Gson;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ClientContactsRepository contactsRepo;
	
	@PostMapping("/")
	public String getUserhome() {
		log.info(getPrincipal()+" user is at home page");
		return "redirect:/user/admin";
	}
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		log.info("inside admin");
		model.addAttribute("companyname", userRepo.findByEmailId(getPrincipal()).getCompanyname());
		return "userhome";
	}
	@GetMapping("/clientcontacts")
	public String getclientcontacts(){
		log.info("getting clientcontacts for "+getPrincipal());
		return "clientcontacts";
	}
	@GetMapping("/gstr3b")
	public String getGSTR3B(){
		log.info("getting gstr3b for "+getPrincipal());
		return "gstr3b";
	}
	@GetMapping("/getContacts")
	@ResponseBody
	public String getContacts() {
		
		return new Gson().toJson(contactsRepo.findByClientemail(getPrincipal()));
	}
	
	@GetMapping("/returnfiling")
	public String getReturnfiling() {
		log.info("getting returnfiling");
		return "returnfiling";
	}
	
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
