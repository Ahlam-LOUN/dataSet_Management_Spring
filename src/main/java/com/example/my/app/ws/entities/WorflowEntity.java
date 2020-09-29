package com.example.my.app.ws.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="workflows")
public class WorflowEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3598223014159079268L;
	
	@javax.persistence.Id
	@GeneratedValue
	private long id;
	@Column(nullable=false,length=60,unique=true)
	private String name;
	
	private String workflowId;
	
	@OneToMany(mappedBy="workflow",cascade = CascadeType.ALL, orphanRemoval=true)
	private List<DataSetEntity> datasets;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	//table intermedaire
	@JoinTable(name="workflows_user",joinColumns= {@JoinColumn(name="workflow_id")},inverseJoinColumns= {@JoinColumn(name="user_id")})
	private List<UserEntity> users;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DataSetEntity> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DataSetEntity> datasets) {
		this.datasets = datasets;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	
	

}
