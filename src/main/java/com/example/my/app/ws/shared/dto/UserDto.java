package com.example.my.app.ws.shared.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 1676605573811449273L;
		private long Id;
        private String firstname;
    	private String lastname;
    	private String email;
    	private String userId;
    	private String password;
		private String emailverificationToken;
    	private Boolean emailverificationStatus=false;
    	private String encryptedPassword;
    	private List<AdressDto> adresses;
    	private  Set<DataSetDto> datasets;
    	private ContactDto contact;
		private Boolean admin;
		public ContactDto getContact() {
			return contact;
		}
		public void setContact(ContactDto contact) {
			this.contact = contact;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Boolean getEmailverificationStatus() {
			return emailverificationStatus;
		}
		public long getId() {
			return Id;
		}
		public void setId(long id) {
			Id = id;
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
		public String getEmailverificationToken() {
			return emailverificationToken;
		}
		public void setEmailverificationToken(String emailverificationToken) {
			this.emailverificationToken = emailverificationToken;
		}
		public Boolean isEmailverificationStatus() {
			return emailverificationStatus;
		}
		public void setEmailverificationStatus(Boolean emailverificationStatus) {
			this.emailverificationStatus = emailverificationStatus;
		}
		public String getEncryptedPassword() {
			return encryptedPassword;
		}
		public void setEncryptedPassword(String encryptedPassword) {
			this.encryptedPassword = encryptedPassword;
		}
		public List<AdressDto> getAdresses() {
			return adresses;
		}
		public void setAdresses(List<AdressDto> adresses) {
			this.adresses = adresses;
		}
		public Boolean getAdmin() {
			return admin;
		}
		public void setAdmin(Boolean admin) {
			this.admin = admin;
		}
		public Set<DataSetDto> getDatasets() {
			return datasets;
		}
		public void setDatasets( Set<DataSetDto> datasets) {
			this.datasets = datasets;
		}
		
    	
}
