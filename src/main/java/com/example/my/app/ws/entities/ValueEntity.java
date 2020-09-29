package com.example.my.app.ws.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="value")
public class ValueEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3798424744757209705L;
	@javax.persistence.Id
	@GeneratedValue
	private long id;
	
	
	@Column(nullable=false,length=60)
	private String valueId;
	
	@Column(nullable=false,length=60)
	private String value;
	
	@ManyToOne
	@JoinColumn(name="attribut_id")
	private AttributEntity attribut;
	
	
	public AttributEntity getAttribut() {
		return attribut;
	}
	public void setAttribut(AttributEntity attribut) {
		this.attribut = attribut;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
