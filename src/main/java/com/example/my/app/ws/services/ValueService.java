package com.example.my.app.ws.services;

import java.util.List;

import com.example.my.app.ws.shared.dto.ValueDto;

public interface ValueService{
	ValueDto  valueCreate(ValueDto valueDto,String id);
	ValueDto getValue(String valueId);
	List<ValueDto> getAllValue(String attributId);
	ValueDto valueUpdate(String valueId,ValueDto valueDto);
	void  valueDelete(String  valueId);

}
