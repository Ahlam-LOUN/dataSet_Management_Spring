package com.example.my.app.ws.controllers;


import java.lang.reflect.Type;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.my.app.ws.requests.AttributRequest;

import com.example.my.app.ws.responses.AttributResponse;
import com.example.my.app.ws.services.AttributService;

import com.example.my.app.ws.shared.dto.AttributDto;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attributs")
public class AttributController {
	@Autowired
	AttributService attributService;
/*
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AttributResponse> storeAttribut(@RequestBody @Valid AttributRequest attributRequest)
			throws Exception {
//if(userRequest.getFirstname().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		ModelMapper modelMapper = new ModelMapper();
		AttributDto attributDto = modelMapper.map(attributRequest, AttributDto.class);
		AttributDto attributCreate = attributService.attributCreate(attributDto);
		AttributResponse attributResponse = modelMapper.map(attributCreate, AttributResponse.class);
// Created for Post ->201
		return new ResponseEntity<AttributResponse>(attributResponse, HttpStatus.CREATED);
	}*/
	
	@PostMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE }
			
			)
	public ResponseEntity<AttributResponse> StoreAttribut(@RequestBody  @Valid AttributRequest attributRequest,@PathVariable String id) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		 AttributDto  attributDto = modelMapper.map( attributRequest,  AttributDto.class);
		 AttributDto createAttribut =  attributService.createAttribut(attributDto,id);
		
		AttributResponse newAttribut = modelMapper.map(createAttribut, AttributResponse.class);
		
		return new ResponseEntity<AttributResponse>(newAttribut, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAttribut(@PathVariable String id) {
		attributService.AttributDelete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<AttributResponse> getOneAttribut(@PathVariable(name="id") String attributId) {
		
		AttributDto attributDto = attributService.getAttribute(attributId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		AttributResponse attributResponse = modelMapper.map(attributDto, AttributResponse.class);
		
		return new ResponseEntity<AttributResponse>(attributResponse, HttpStatus.OK);
	}
	
	@GetMapping("/datasets/{id}")
	public ResponseEntity<List<AttributResponse>>getAttributes(@PathVariable(name="id") String dataSetId) {
		
		List<AttributDto> attributes = attributService.getAllAttributes(dataSetId);
		
		Type listType = new TypeToken<List<AttributResponse>>() {}.getType();
		List<AttributResponse> attributResponse = new ModelMapper().map(attributes, listType);
		
		return new ResponseEntity<List<AttributResponse>>(attributResponse, HttpStatus.OK);
		
	}

}
