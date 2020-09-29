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

import com.example.my.app.ws.exceptions.UserException;
import com.example.my.app.ws.requests.DatasetRequest;
import com.example.my.app.ws.responses.DataSetRespone;
import com.example.my.app.ws.responses.ErrorMessages;
import com.example.my.app.ws.responses.UserResponse;
import com.example.my.app.ws.responses.WorkflowResponse;
import com.example.my.app.ws.services.DataSetService;
import com.example.my.app.ws.shared.dto.DataSetDto;
import com.example.my.app.ws.shared.dto.UserDto;
import com.example.my.app.ws.shared.dto.WorkflowDto;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/datasets")
public class DataSetController {

	@Autowired
	DataSetService dataSetService;
	@GetMapping(produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<DataSetRespone>> getDatasets() {
		List<DataSetDto> datasets = dataSetService.getAllDataSet();
		Type listType = new TypeToken<List<DataSetRespone>>() {}.getType();
		List<DataSetRespone> datasetsResponse = new ModelMapper().map(datasets, listType);
		return new ResponseEntity<List<DataSetRespone>>( datasetsResponse ,HttpStatus.OK);

	}
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<DataSetRespone> createDataSey(@RequestBody @Valid DatasetRequest dataSetRequest)
			throws Exception {
		if (dataSetRequest.getName().isEmpty())
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		ModelMapper modelMapper = new ModelMapper();
		DataSetDto dataSetDto = modelMapper.map(dataSetRequest, DataSetDto.class);
		DataSetDto userCreate = dataSetService.dataSetCreate(dataSetDto);
		DataSetRespone dataResponse = modelMapper.map(userCreate, DataSetRespone.class);
// Created for Post ->201
		return new ResponseEntity<DataSetRespone>(dataResponse, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping(path="/{id}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<DataSetRespone> getOneDataSet(@PathVariable String id) {// Pathvariable c'est a dire que Id va
																			// recuperrer sa valeur depuis le segment
																			// dynamique
		DataSetDto dataSetDto = dataSetService.getDataSet(id);
		ModelMapper modelMapper = new ModelMapper();
		DataSetRespone dataSetRespone = modelMapper.map(dataSetDto, DataSetRespone.class);
		// OK for Get ->200
		return new ResponseEntity<DataSetRespone>(dataSetRespone, HttpStatus.OK);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDataSet(@PathVariable String id) {
		dataSetService.DataSetDelete(id);
		// NO_CONTENT for delete 204
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path = "/{datasetid}/{workflowid}",
			produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<DataSetRespone> updateDataset(@PathVariable String workflowid,@PathVariable String datasetid) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		DataSetDto updateDataset = dataSetService.updateDataset(workflowid, datasetid);
		DataSetRespone datasetResponse =  modelMapper.map(updateDataset, DataSetRespone.class);
		return new ResponseEntity<DataSetRespone>(datasetResponse, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/dataset-manage/{id}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<DataSetRespone>> getDatasetInworkflow(@PathVariable String id) {// Pathvariable c'est a dire que Id va
																			// recuperrer sa valeur depuis le segment
																			// dynamique
		List<DataSetDto> dataDto = dataSetService.getDataSets(id);
		Type listType = new TypeToken<List<DataSetRespone>>() {}.getType();
		List<DataSetRespone> dataResponse = new ModelMapper().map(dataDto, listType);
		// OK for Get ->200
		return new ResponseEntity<List<DataSetRespone>>(dataResponse, HttpStatus.OK);

	}
	@GetMapping(path="/dataset-out",produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<DataSetRespone>> getDatasetworkflow() {// Pathvariable c'est a dire que Id va
																			// recuperrer sa valeur depuis le segment
																			// dynamique
		List<DataSetDto> dataDto = dataSetService.getDataSetsOut();
		Type listType = new TypeToken<List<DataSetRespone>>() {}.getType();
		List<DataSetRespone> dataResponse = new ModelMapper().map(dataDto, listType);
		// OK for Get ->200
		return new ResponseEntity<List<DataSetRespone>>(dataResponse, HttpStatus.OK);

	}

}
