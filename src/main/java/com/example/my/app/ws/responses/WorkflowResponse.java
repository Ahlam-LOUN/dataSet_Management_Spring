package com.example.my.app.ws.responses;

import java.util.List;

import com.example.my.app.ws.requests.DatasetRequest;
import com.example.my.app.ws.requests.UserRequest;

public class WorkflowResponse {

	private String workflowId;
	private String name;
	private List<UserRequest> users;
	private List<DatasetRequest> datases;

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

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
