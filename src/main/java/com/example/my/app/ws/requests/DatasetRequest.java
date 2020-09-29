package com.example.my.app.ws.requests;

import java.util.List;
import java.util.Set;


public class DatasetRequest {
	private String name;
	private String location;
	private List<AttributRequest> attributs;
	private Set<UserRequest> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<AttributRequest> getAttributs() {
		return attributs;
	}

	public void setAttributs(List<AttributRequest> attributs) {
		this.attributs = attributs;
	}

	public Set<UserRequest> getUsers() {
		return users;
	}

	public void setUsers(Set<UserRequest> users) {
		this.users = users;
	}
 
	
}
