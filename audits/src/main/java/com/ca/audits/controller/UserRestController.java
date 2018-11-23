package com.ca.audits.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ca.audits.model.ClientContacts;
import com.ca.audits.repo.ClientContactsRepository;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	ClientContactsRepository contactsRepo;

	@PostMapping("/addNewContact")
	public String addNewContact(ClientContacts contact) {
		logger.info("inside addNewContact " + contact.getEmail());
		contact.setClientemail(getPrincipal());
		contactsRepo.save(contact);
		return "success";
	}

	@DeleteMapping("/deleteContact/{contactid}")
	@ResponseBody
	public void deleteContact(@PathVariable String contactid) {
		logger.info("the contact is deleted " + contactid);
		contactsRepo.deleteById(Integer.parseInt(contactid));
//		return "success";
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
