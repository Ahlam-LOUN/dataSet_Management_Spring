package com.example.my.app.ws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity(name="contacts")
public class ContactEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 500647786558226117L;
	@Id
	@GeneratedValue
	private long id;
	@Column(length = 30)
	@NotBlank
	private String contactId;

	@NotBlank
	private String mobile;
    
	@OneToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	private String skype;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
