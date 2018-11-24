package com.ca.audits.repo;

import org.springframework.data.repository.CrudRepository;

import com.ca.audits.model.GSTR3BTask;
import java.lang.String;
import java.util.List;

public interface GSTR3BTaskRepository extends CrudRepository<GSTR3BTask, Integer>{
	List<GSTR3BTask> findByAdminemail(String adminemail);
}
