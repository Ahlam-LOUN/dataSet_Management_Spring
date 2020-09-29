package com.example.my.app.ws.services;

import java.util.List;

import com.example.my.app.ws.shared.dto.AttributDto;

public interface AttributService {
	AttributDto getAttributByAttributId(String attributId);
	void  AttributDelete(String  attributId);
	
	AttributDto attributCreate(AttributDto attributDto, String id);
	AttributDto createAttribut(AttributDto attribut, String name);
	List<AttributDto> getAllAttributes(String datasetId);
	AttributDto getAttribute(String attributeId);
}
