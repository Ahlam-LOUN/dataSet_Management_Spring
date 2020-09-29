package com.example.my.app.ws.responses;

import java.util.List;

import com.example.my.app.ws.requests.DataType;

public class AttributResponse {
	private String attributId;
	private String name;
	private List<ValueResponse> values;
	
	//private DataType datatype;
	private String datatype;
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

	public List<ValueResponse> getValues() {
		return values;
	}

	public void setValues(List<ValueResponse> values) {
		this.values = values;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

}
