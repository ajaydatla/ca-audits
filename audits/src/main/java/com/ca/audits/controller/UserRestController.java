package com.ca.audits.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ca.audits.model.ClientContacts;
import com.ca.audits.repo.ClientContactsRepository;
import com.ca.audits.repo.GSTR3BTaskRepository;
import com.google.gson.Gson;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	ClientContactsRepository contactsRepo;
	
	@Autowired
	GSTR3BTaskRepository gstr3bRepo;

	@PostMapping("/addNewContact")
	public String addNewContact(ClientContacts contact) {
		logger.info("inside addNewContact " + contact.getEmail());
		contact.setClientemail(getPrincipal());
		contactsRepo.save(contact);
		return "success";
	}
	@GetMapping("/getContacts")
	public String getContacts() {
		return new Gson().toJson(contactsRepo.findByClientemail(getPrincipal()));
	}

	@DeleteMapping("/deleteContact/{contactid}")
	public void deleteContact(@PathVariable String contactid) { 
		logger.info("the contact is deleted " + contactid);
		contactsRepo.deleteById(Integer.parseInt(contactid));
	}
	@GetMapping("/getGSTR3BCompletedTask")
	public String getGSTR3BCompletedTask() {
		return new Gson().toJson(gstr3bRepo.findByAdminemail(getPrincipal()));
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
	@GetMapping("/getSideBarMenu")
	public String getSideBarMenu() {
		return "[\r\n" + 
				"  {\r\n" + 
				"    \"iconcssclass\": \"sidebaricon glyphicon glyphicon-home\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"iconcssclass\": \"sidebaricon glyphicon glyphicon-th-list\",\r\n" + 
				"    \"iconlink\": \"\",\r\n" + 
				"    \"mainhead\": {\r\n" + 
				"      \"GST\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"GSTR 3B\": {\"link\":\"#!gstr3b\"},\r\n" + 
				"          \"GSTR 1\": {},\r\n" + 
				"          \"GSTR 4\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"INCOME TAX\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"RETURN FILING\": {},\r\n" + 
				"          \"Refund Matters / Demand Matters / Defective Return Notices\": {\r\n" + 
				"            \"style\": \"font-size:12px;padding-top: 3px;\"\r\n" + 
				"          },\r\n" + 
				"          \"Non Filing of Return Notices\": {},\r\n" + 
				"          \"15G/H\": {},\r\n" + 
				"          \"Income Tax Notice\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"AUDIT\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"TAX AUDIT\": {},\r\n" + 
				"          \"SCHOOL AUDIT\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"TDS\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"TDS Return Filing\": {},\r\n" + 
				"          \"Correction Returns\": {},\r\n" + 
				"          \"Default Corrections\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"MISCELLANEOUS\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"Deed Writing\": {},\r\n" + 
				"          \"Certificates\": {},\r\n" + 
				"          \"PAN / TAN\": {},\r\n" + 
				"          \"Digital Signature\": {},\r\n" + 
				"          \"Projection\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"FINANCE\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"PROJECT REPORT\": {},\r\n" + 
				"          \"CMA\": {},\r\n" + 
				"          \"PROPOSAL\": {}\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"iconcssclass\": \"sidebaricon glyphicon glyphicon-envelope\",\r\n" + 
				"    \"iconlink\": \"\",\r\n" + 
				"    \"mainhead\": {\r\n" + 
				"      \"ADD/VIEW CONTACT\": {\r\n" + 
				"        \"link\":\"#!clientcontacts\"\r\n" + 
				"      },\r\n" + 
				"      \"ADD/VIEW PHONE BOOK CATEGORY\": {\r\n" + 
				"        \"style\": \"font-size:12px;padding-top: 3px;\"\r\n" + 
				"      },\r\n" + 
				"      \"MANAGE TEMPLATE\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"GENERATE TEMPLATE\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"CREATE LIST\": {},\r\n" + 
				"      \"VIEW LIST\": {},\r\n" + 
				"      \"SMS GENERATOR\": {},\r\n" + 
				"      \"EMAIL-LOG\": {},\r\n" + 
				"      \"SMS-LOG\": {}\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"iconcssclass\": \"fa fa-inr sidebaricon\",\r\n" + 
				"    \"mainhead\": {\r\n" + 
				"      \"BILLING SETTINGS\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"MANAGE ORGANIZATION\": {},\r\n" + 
				"          \"MANAGE ACCOUNTS\": {},\r\n" + 
				"          \"MANAGE RECEIPT BOOKS\": {},\r\n" + 
				"          \"MANAGE TAX\": {},\r\n" + 
				"          \"NARRATIN, NOTES & SAC\": {},\r\n" + 
				"          \"CONFIGURE AUTO RECEIPT NO\": {\"style\":\"font-size:12px;\"}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"OPENING BILLING\": {},\r\n" + 
				"      \"BILLS\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"GENERATE INDIVIDUAL BILLS\": {\"style\":\"font-size:13px\"},\r\n" + 
				"          \"GENERATE BILL WITHOUT TASK\": {\"style\":\"font-size:12px\"},\r\n" + 
				"          \"GENERATE COMBINE BILLS\": {},\r\n" + 
				"          \"DELETE BILLS\": {},\r\n" + 
				"          \"MULTIPLE SERVICES BILL\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"RECEIPTS\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"IND. RECEIPT\": {},\r\n" + 
				"          \"GROUP RECEIPTS\": {},\r\n" + 
				"          \"ADVANCE/DIRECT INVOICE & RECEIPT\": {\"style\":\"font-size:10px\"},\r\n" + 
				"          \"SETTLING ADVANCE RECEIPT\": {\"style\":\"font-size:13px\"},\r\n" + 
				"          \"DELETE RECEIPTS\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"REPORTS\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"FREE TASK REPORT\": {},\r\n" + 
				"          \"UNBILLED TASK REPORT\": {},\r\n" + 
				"          \"BILL REGISTER\": {},\r\n" + 
				"          \"RECEIPT REGISTER\": {},\r\n" + 
				"          \"SEARCH BILLS\": {},\r\n" + 
				"          \"SEARCH COLLECTION\": {},\r\n" + 
				"          \"BILL SUMMARY\": {},\r\n" + 
				"          \"RECEIPT SUMMARY\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"BILLING DASHBOARD\": {\r\n" + 
				"        \"subhead\": {\r\n" + 
				"          \"PROJECT REPORT\": {},\r\n" + 
				"          \"CMA\": {},\r\n" + 
				"          \"PROPOSAL\": {}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"VIEW ACCOUNTS\":{},\r\n" + 
				"      \"MARK THE TASK FREE\":{},\r\n" + 
				"      \"BILLING\":{}\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  {\"iconcssclass\":\"sidebaricon glyphicon glyphicon-th\",\r\n" + 
				"    \"mainhead\":{\"NOTICE BOARD\":{\r\n" + 
				"      \"subhead\":{\r\n" + 
				"        \"CREATE NOTICE BOARD MESSAGE\":{\"style\":\"font-size:11px;\"},\r\n" + 
				"        \"CREATED BY ME\":{},\r\n" + 
				"        \"POSTED FOR ME\":{}\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"      \"LEAVE MANAGEMENT\":{\r\n" + 
				"      \"subhead\":{\r\n" + 
				"        \"LEAVES PENDING FOR APPROVAL\":{},\r\n" + 
				"        \"STAFF LEAVE DASHBOARD\":{},\r\n" + 
				"        \"MODIFY LEAVE BALANCES\":{}\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    \"DIGITAL SIGNATURE REGISTER\":{\"style\":\"font-size:10px;\"}\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  {\"iconcssclass\":\"sidebaricon glyphicon glyphicon-hourglass\",\r\n" + 
				"    \"mainhead\":{\"SHOW ALL\":{},\r\n" + 
				"      \"ATTACH A DOCUMENT\":{},\r\n" + 
				"      \"ATTACHED BY ME\":{},\r\n" + 
				"      \"ATTACHED FOR ME\":{}\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  {\"iconcssclass\":\"sidebaricon glyphicon glyphicon-bitcoin\",\r\n" + 
				"    \"mainhead\":{\"CREATE DOCUMENT\":{},\r\n" + 
				"      \"CREATED BY ME\":{},\r\n" + 
				"      \"SHARED TO ME\":{}\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  {\"iconcssclass\":\"sidebaricon glyphicon glyphicon-cog\",\r\n" + 
				"    \"mainhead\":{\"TASK SETTINGS\":{\r\n" + 
				"      \"subhead\":{\r\n" + 
				"        \"CLIENT LIST\":{},\r\n" + 
				"        \"MANAGE TYPES\":{},\r\n" + 
				"        \"MANAGE CATEGORIES\":{},\r\n" + 
				"        \"GROUP MASTER\":{}\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"      \"USER MANAGEMENT\":{},\r\n" + 
				"      \"CHECK CONTROLS\":{},\r\n" + 
				"      \"AUTO SMS CONFIGURATION\":{\"style\":\"font-size: 11px;\"},\r\n" + 
				"      \"MANAGE CHECKS\":{\r\n" + 
				"        \"subhead\":{\r\n" + 
				"          \"MANAGE TASK CHECKS\":{},\r\n" + 
				"          \"MANAGE CHECK GROUPS\":{}\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"IMPORT MASTERS\":{},\r\n" + 
				"      \"BULK TASK CREATION\":{}\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  {\"iconcssclass\":\"sidebaricon glyphicon glyphicon-list-alt\",\r\n" + 
				"    \"mainhead\":{\r\n" + 
				"      \"INWARD-REGISTER\":{},\r\n" + 
				"      \"SETTING\":{\r\n" + 
				"        \"subhead\":{\r\n" + 
				"          \"CREATE/EDIT INWARD REGISTER\":{\"style\":\"font-size: 14px;padding-top: 2px;\"},\r\n" + 
				"          \"DOCUMENT NATURE & THROUGH\":{\"style\":\"font-size: 14px;padding-top: 2px;\"}\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"]";
	}
}
