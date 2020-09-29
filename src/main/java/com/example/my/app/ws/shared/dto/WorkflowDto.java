package com.example.my.app.ws.shared.dto;

import java.io.Serializable;
import java.util.List;


public class WorkflowDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3768771979258669242L;
	private long id;
	private String workflowId;
	private String name;
	private List<UserDto> users;
	private List<DataSetDto> datasets;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	public List<DataSetDto> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DataSetDto> datasets) {
		this.datasets = datasets;
	}

	

}
