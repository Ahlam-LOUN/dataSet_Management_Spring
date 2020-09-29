package com.example.my.app.ws.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
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

import com.example.my.app.ws.exceptions.UserException;
import com.example.my.app.ws.requests.AdressRequest;
import com.example.my.app.ws.requests.UserRequest;
import com.example.my.app.ws.responses.AdressResponse;
import com.example.my.app.ws.responses.ErrorMessages;
import com.example.my.app.ws.responses.UserResponse;
import com.example.my.app.ws.services.AdressService;
import com.example.my.app.ws.shared.dto.AdressDto;
import com.example.my.app.ws.shared.dto.UserDto;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/adresses")
public class AdressController {
	@Autowired
	AdressService adressService;
   @CrossOrigin(origins="*")
   @GetMapping(produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<AdressResponse>> getAdresses(Principal principal) {
		List<AdressDto> adresses = adressService.getAllAdresses(principal.getName());
		//ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<AdressResponse>>() {}.getType();
		List<AdressResponse> adressesResponse = new ModelMapper().map(adresses, listType);
		return new ResponseEntity<List<AdressResponse>>(adressesResponse,HttpStatus.OK);

	}
@PostMapping(consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
public ResponseEntity<AdressResponse> storeAdress(@RequestBody @Valid AdressRequest adressRequest,Principal principal) throws Exception {
//if(userRequest.getFirstname().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
ModelMapper modelMapper = new ModelMapper();
AdressDto adressDto = modelMapper.map(adressRequest, AdressDto.class);
AdressDto adressCreate = adressService.adressCreate(adressDto,principal.getName());
AdressResponse adressResponse = modelMapper.map(adressCreate, AdressResponse.class);
// Created for Post ->201
return new ResponseEntity<AdressResponse>(adressResponse , HttpStatus.CREATED);
}

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteAdress(@PathVariable String id) {
	
	adressService.adressDelete(id);
	// NO_CONTENT for delete 204
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@GetMapping(path="/{id}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
public ResponseEntity<AdressResponse> getOneAdress(@PathVariable String id) {// Pathvariable c'est a dire que Id va
																		// recuperrer sa valeur depuis le segment
																		// dynamique
	AdressDto adressDto = adressService.getAdress(id);
	ModelMapper modelMapper = new ModelMapper();
	AdressResponse adressResponse = modelMapper.map(adressDto, AdressResponse.class);
	// OK for Get ->200
	return new ResponseEntity<AdressResponse>(adressResponse, HttpStatus.OK);

}

}
