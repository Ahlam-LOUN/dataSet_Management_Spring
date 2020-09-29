package com.example.my.app.ws.requests;

public enum DataType {
	  INTEGER("int"),
      LONG("long"),
      DOUBLE("double"),
      FLOAT("float"),
      STRING("String"),
      DATE("Date"),
      BOOLEAN("Boolean"),
      CHAR("Char");
      
      private String datatype;
      
	DataType(String datatype) {
	    this.datatype=datatype;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
      
    		  
      
	
}
