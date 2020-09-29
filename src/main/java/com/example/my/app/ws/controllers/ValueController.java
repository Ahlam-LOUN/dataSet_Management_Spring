package com.example.my.app.ws.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.my.app.ws.requests.ValueRequest;
import com.example.my.app.ws.responses.ValueResponse;
import com.example.my.app.ws.services.ValueService;
import com.example.my.app.ws.shared.dto.ValueDto;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/values")
public class ValueController {
	@Autowired
	ValueService valueService;
	
	@PostMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE }
			
			)
	public ResponseEntity<ValueResponse> StoreValue(@RequestBody  @Valid ValueRequest valueRequest,@PathVariable String id) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		ValueDto  valueDto = modelMapper.map( valueRequest, ValueDto.class);
		ValueDto createValue =  valueService.valueCreate(valueDto,id);
		ValueResponse newValue = modelMapper.map(createValue, ValueResponse.class);
		
		return new ResponseEntity<ValueResponse>(newValue, HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{id}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ValueResponse> getOneValue(@PathVariable String id) {// Pathvariable c'est a dire que Id va
																			// recuperrer sa valeur depuis le segment
																			// dynamique
		ValueDto valueDto = valueService.getValue(id);
		ModelMapper modelMapper = new ModelMapper();
		ValueResponse valueResponse = modelMapper.map(valueDto, ValueResponse.class);
		// OK for Get ->200
		return new ResponseEntity<ValueResponse>(valueResponse, HttpStatus.OK);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteValue(@PathVariable String id) {
		valueService.valueDelete(id);
		// NO_CONTENT for delete 204
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	 @GetMapping(path="/attribut/{id}",produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<List<ValueResponse>> getValues( @PathVariable  String id) {
			List<ValueDto> values = valueService.getAllValue(id);
			//ModelMapper modelMapper = new ModelMapper();
			Type listType = new TypeToken<List<ValueResponse>>() {}.getType();
			List<ValueResponse> valuesResponse = new ModelMapper().map(values, listType);
			return new ResponseEntity<List<ValueResponse>>(valuesResponse,HttpStatus.OK);

		}
	 @PutMapping(path="/{id}",
			    consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
		        produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ValueResponse> updateValue(@PathVariable String id, @RequestBody ValueRequest valueRequest) {
		// Presentation Layer
		ModelMapper modelMapper = new ModelMapper();
		ValueDto valueDto= modelMapper.map(valueRequest, ValueDto.class);
		
		// Server Layer
		ValueDto valueUpdate = valueService.valueUpdate(id, valueDto);
		ModelMapper modelMappe = new ModelMapper();
		ValueResponse valueResponse= modelMappe.map(valueUpdate, ValueResponse.class);
		// Accepted for Put ->202
		return new ResponseEntity<ValueResponse>(valueResponse, HttpStatus.ACCEPTED);
	}

}
