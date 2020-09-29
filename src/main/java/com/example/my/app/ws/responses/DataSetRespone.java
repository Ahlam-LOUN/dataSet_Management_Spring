package com.example.my.app.ws.responses;

import java.util.List;
import java.util.Set;

import com.example.my.app.ws.requests.AttributRequest;
import com.example.my.app.ws.shared.dto.UserDto;

public class DataSetRespone {
	private String dataSetId;
	private String name;
	private String location;
	private List<AttributResponse> attributs;

	
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

	public List<AttributResponse> getAttributs() {
		return attributs;
	}

	public void setAttributs(List<AttributResponse> attributs) {
		this.attributs = attributs;
	}

}
