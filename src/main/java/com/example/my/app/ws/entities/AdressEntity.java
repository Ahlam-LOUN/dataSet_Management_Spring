package com.example.my.app.ws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="adresses")
public class AdressEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8541548370407121991L;
	@javax.persistence.Id
	@GeneratedValue
	private long id;
	@Column(nullable=false)
	private String adressId;
	@Column(length=40,nullable=false)
	private String city;
	@Column(length=40,nullable=false)
	private String street;
	@Column(length=40,nullable=false)
	private String country;
	@Column(length=40,nullable=false)
	private String code_postal;
	@Column(length=40,nullable=false)
	private String type;
	// plusieur adresses pour chaque user
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getAdressId() {
		return adressId;
	}
	public void setAdressId(String adressId) {
		this.adressId = adressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}


}
