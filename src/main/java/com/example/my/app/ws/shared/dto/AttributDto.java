package com.example.my.app.ws.shared.dto;

import java.io.Serializable;
import java.util.List;

import com.example.my.app.ws.requests.DataType;

public class AttributDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7044124636424275134L;
	private long id;
	private String attributId;
	private String name;
	private List<ValueDto> values;
//	private DataType datatype;
	private String datatype;
	private DataSetDto dataset;

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

	
	public List<ValueDto> getValues() {
		return values;
	}

	public void setValues(List<ValueDto> values) {
		this.values = values;
	}

	public DataSetDto getDataset() {
		return dataset;
	}

	public void setDataset(DataSetDto dataset) {
		this.dataset = dataset;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	

}
