package com.example.my.app.ws.requests;

import java.util.List;

public class AttributRequest {
 private String name;
 private String datatype;
 private List<ValueRequest> values;
 //private DataType datatype;
 
 
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDatatype() {
	return datatype;
}
public void setDatatype(String datatype) {
	this.datatype = datatype;
}
public List<ValueRequest> getValues() {
	return values;
}
public void setValues(List<ValueRequest> values) {
	this.values = values;
}

}
