package com.example.my.app.ws.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;

@Entity(name="groups")
public class GroupEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2685514302711756904L;
	@javax.persistence.Id
	@GeneratedValue // for  auto incrementig 
	private long id;
	@Column(name="name",length=60)
	private String name;
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	//table intermedaire
	@JoinTable(name="groups_user",joinColumns= {@JoinColumn(name="groups_id")},inverseJoinColumns= {@JoinColumn(name="user_id")})
	private Set<UserEntity> usersG= new HashSet<>();
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
	public Set<UserEntity> getUsers() {
		return usersG;
	}
	public void setUsers(Set<UserEntity> usersG) {
		this.usersG = usersG;
	}

}
