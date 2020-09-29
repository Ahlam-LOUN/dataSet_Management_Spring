package com.example.my.app.ws.shared.dto;

import java.io.Serializable;

public class ValueDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8992131813472230101L;
	
	private long id;
	private String valueId;
	private String value;
	
	
	private AttributDto attribut;
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
	public AttributDto getAttribut() {
		return attribut;
	}
	public void setAttribut(AttributDto attribut) {
		this.attribut = attribut;
	}
	
	

}
