package com.example.my.app.ws.requests;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WorkflowRequest {

	@NotNull(message = "Ce champs ne doit pas etre null")
	@NotBlank(message = "Ce champs ne doit pas etre vide !")
	private String name;
	private List<UserRequest> users;
	private List<DatasetRequest> datases;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserRequest> getUsers() {
		return users;
	}

	public void setUsers(List<UserRequest> users) {
		this.users = users;
	}

	public List<DatasetRequest> getDatases() {
		return datases;
	}

	public void setDatases(List<DatasetRequest> datases) {
		this.datases = datases;
	}

}
