package com.example.my.app.ws.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.my.app.ws.requests.DataType;

@Entity(name="attributs")
public class AttributEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6965874522407230961L;
	@javax.persistence.Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false,length=60)
	private String attributId;
	@Column(nullable=false,length=60)
	private String name;
	//@OneToMany(mappedBy="dataSet",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	//private DataType datatype;
	private String datatype;
	@ManyToOne
	@JoinColumn(name="data_Set_id")
	private DataSetEntity dataSet;

	
	@OneToMany(mappedBy="attribut",cascade = CascadeType.ALL,orphanRemoval=true)
	private List<ValueEntity> values;
	
	public List<ValueEntity> getValues() {
		return values;
	}

	public void setValues(List<ValueEntity> values) {
		this.values = values;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAttributId() {
		return attributId;
	}

	public void setAttributId(String attributId) {
		this.attributId = attributId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public DataSetEntity getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSetEntity dataSet) {
		this.dataSet = dataSet;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
	
	

}
