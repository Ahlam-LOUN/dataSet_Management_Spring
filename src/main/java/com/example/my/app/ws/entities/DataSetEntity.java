package com.example.my.app.ws.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "datasets")
public class DataSetEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3299442468603453742L;
	@javax.persistence.Id
	@GeneratedValue
	private long id;
	@Column(nullable = false, length = 60)
	private String dataSetId;
	@Column(nullable = false, length = 60, unique = true)
	private String name;
	@Column(nullable = false, length = 60)
	private String location;

	@OneToMany(mappedBy = "dataSet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AttributEntity> attributs;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// table intermedaire
	@JoinTable(name = "datasets_user", joinColumns = { @JoinColumn(name = "data_set_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private Set<UserEntity> users = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "workflow_id")
	private WorflowEntity workflow;

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

	public List<AttributEntity> getAttributs() {
		return attributs;
	}

	public void setAttributs(List<AttributEntity> attributs) {
		this.attributs = attributs;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	public WorflowEntity getWorkflow() {
		return workflow;
	}

	public void setWorkflow(WorflowEntity workflow) {
		this.workflow = workflow;
	}

}
