package com.example.my.app.ws.requests;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	@NotBlank(message="Ce champs ne doit pas etre vide !")
	@Size(min=3,message="Le champs doit avoir au minimum 3 caractères !!")
	private String firstname;
	@NotNull(message="Ce champs ne doit pas etre null")
	@Size(min=3,message="Le champs doit avoir au minimum 3 caractères !!")
	private String lastname;
	@NotNull(message="Ce champs ne doit pas etre null")
	@Email(message="Le champs doit respecter le format Email !!")
	private String email;
	@NotNull(message="Ce champs ne doit pas etre null")
	@Size(min=8,message="Le champs doit avoir au minimum 8 caractères !!")
	@Size(max=12,message="Le champs doit avoir au maximum 12 caractères !!")
	@Pattern(regexp="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",message="Le mot de passe doit contenir des Maj et Min Caratères et aussi des nombres")
	private String password;
	
	private Boolean admin;
	
	private Set<DatasetRequest> datasets;
	
	
	private ContactRequest contact;
	public ContactRequest getContact() {
		return contact;
	}
	public void setContact(ContactRequest contact) {
		this.contact = contact;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public  Set<DatasetRequest> getDatasets() {
		return datasets;
	}
	public void setDatasets( Set<DatasetRequest> datasets) {
		this.datasets = datasets;
	}



}
