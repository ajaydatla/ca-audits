package com.ca.audits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "gstr3btask")
public class GSTR3BTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int taskid;

	private String clientname;
	
	private String fileno;
	
	private String pendingissues;
	
	private String description;
	
	private String startdate;
	
	private String expecteddateofcompletion;
	
	private String assignedto;
	
	private String financialyear;
	
	private String period;
	
	private String groupname;
	
	private String taskleader;
	
	private String completedcheck;
	
	private String completedby;
	
	private String currentposition;
	
	private String adminemail;

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getFileno() {
		return fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
	}

	public String getPendingissues() {
		return pendingissues;
	}

	public void setPendingissues(String pendingissues) {
		this.pendingissues = pendingissues;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getExpecteddateofcompletion() {
		return expecteddateofcompletion;
	}

	public void setExpecteddateofcompletion(String expecteddateofcompletion) {
		this.expecteddateofcompletion = expecteddateofcompletion;
	}

	public String getAssignedto() {
		return assignedto;
	}

	public void setAssignedto(String assignedto) {
		this.assignedto = assignedto;
	}

	public String getFinancialyear() {
		return financialyear;
	}

	public void setFinancialyear(String financialyear) {
		this.financialyear = financialyear;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getTaskleader() {
		return taskleader;
	}

	public void setTaskleader(String taskleader) {
		this.taskleader = taskleader;
	}

	public String getCompletedcheck() {
		return completedcheck;
	}

	public void setCompletedcheck(String completedcheck) {
		this.completedcheck = completedcheck;
	}

	public String getCompletedby() {
		return completedby;
	}

	public void setCompletedby(String completedby) {
		this.completedby = completedby;
	}

	public String getCurrentposition() {
		return currentposition;
	}

	public void setCurrentposition(String currentposition) {
		this.currentposition = currentposition;
	}

	public String getAdminemail() {
		return adminemail;
	}

	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}
	
	
}
