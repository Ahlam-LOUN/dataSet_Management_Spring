package com.example.my.app.ws.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="user")
public class UserEntity implements Serializable{
	private static final long serialVersionUID = -2624711397208317915L;
	@javax.persistence.Id
	@GeneratedValue 
	// for  auto incrementig 
	private long Id;
	@Column(nullable=false,length=60)
    private String firstname;
	@Column(nullable=false,length=60)
	private String lastname;
	@Column(nullable=true,length=160,unique=true)
	private String email;
	@Column(nullable=false)
	private String userId;
	@Column(nullable=true)
	private String emailverificationToken;
	
	@Column(nullable=false)
	private Boolean emailverificationStatus=false;
	
	@Column(nullable=false)
	private String encryptedPassword;
	@Column(nullable=true)
	private Boolean admin=false;
	
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private List<AdressEntity> adresses;
	
	@OneToOne(mappedBy="user",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private ContactEntity contact;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="users")
	private List<WorflowEntity> workflows;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="users")
	private Set<DataSetEntity> datasets=new HashSet<>();
	
	
	
	
	
	
	public List<AdressEntity> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<AdressEntity> adresses) {
		this.adresses = adresses;
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
	public Boolean getEmailverificationStatus() {
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
	public ContactEntity getContact() {
		return contact;
	}
	public void setContact(ContactEntity contact) {
		this.contact = contact;
	}
	
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Set<DataSetEntity> getDataSet() {
		return datasets;
	}
	public void setDataSet(Set<DataSetEntity> datasets) {
		this.datasets= datasets;
	}
	public List<WorflowEntity> getWorkflows() {
		return workflows;
	}
	public void setWorkflows(List<WorflowEntity> workflows) {
		this.workflows = workflows;
	}


}
