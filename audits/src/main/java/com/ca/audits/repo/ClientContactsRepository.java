package com.ca.audits.repo;

import org.springframework.data.repository.CrudRepository;

import com.ca.audits.model.ClientContacts;
import java.util.List;
import java.lang.String;

public interface ClientContactsRepository extends CrudRepository<ClientContacts, Integer>{
	
	List<ClientContacts> findByClientemail(String clientemail);

}
