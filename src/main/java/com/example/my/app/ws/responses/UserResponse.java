package com.example.my.app.ws.responses;

import java.util.List;
import java.util.Set;

import com.example.my.app.ws.requests.DatasetRequest;

public class UserResponse {
	private String firstname;
	private String lastname;
	private String email;
	private String userId;
	
	private  Set<DataSetRespone> datasets;

	private ContactResponse contact;
	private Boolean admin;
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public ContactResponse getContact() {
		return contact;
	}
	public void setContact(ContactResponse contact) {
		this.contact = contact;
	}
	public  Set<DataSetRespone> getDatasets() {
		return datasets;
	}
	public void setDatasets( Set<DataSetRespone> datasets) {
		this.datasets = datasets;
	}
	
}
