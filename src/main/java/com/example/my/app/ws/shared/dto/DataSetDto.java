package com.example.my.app.ws.shared.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.example.my.app.ws.requests.AttributRequest;

public class DataSetDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7549763629562090430L;
	private long id;
	private String dataSetId;
	private String name;
	private String location;
	private List<AttributDto> attributs;
	private WorkflowDto workflow;
	//private  Set<UserDto> users; *bdtl hna **

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDataSetId() {
		return dataSetId;
	}

	public void setDataSetId(String dataSetId) {
		this.dataSetId = dataSetId;
	}

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

	public List<AttributDto> getAttributs() {
		return attributs;
	}

	public void setAttributs(List<AttributDto> attributs) {
		this.attributs = attributs;
	}

	public WorkflowDto getWorkflow() {
		return workflow;
	}

	public void setWorkflow(WorkflowDto workflow) {
		this.workflow = workflow;
	}

	/*public Set<UserDto> getUsers() {
		return users;
	}

	public void setUsers( Set<UserDto> users) {
		this.users = users;
	}*/

	

}
