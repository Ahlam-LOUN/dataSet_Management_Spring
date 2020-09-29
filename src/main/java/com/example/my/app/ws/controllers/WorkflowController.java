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
import com.example.my.app.ws.requests.WorkflowRequest;
import com.example.my.app.ws.responses.AdressResponse;
import com.example.my.app.ws.responses.ErrorMessages;
import com.example.my.app.ws.responses.UserResponse;
import com.example.my.app.ws.responses.WorkflowResponse;
import com.example.my.app.ws.services.WorkflowService;
import com.example.my.app.ws.shared.dto.AdressDto;
import com.example.my.app.ws.shared.dto.UserDto;
import com.example.my.app.ws.shared.dto.WorkflowDto;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/workflows")
public class WorkflowController {
	
	@Autowired
	WorkflowService workflowService;
	
	  @GetMapping(produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<List<WorkflowResponse>> getWorkflows(Principal principal) {
			List<WorkflowDto> workflows =  workflowService.getAllworkflows(principal.getName());
			//ModelMapper modelMapper = new ModelMapper();
			Type listType = new TypeToken<List<WorkflowResponse>>() {}.getType();
			List<WorkflowResponse> workflowsResponse = new ModelMapper().map(workflows, listType);
			return new ResponseEntity<List<WorkflowResponse>>(workflowsResponse,HttpStatus.OK);

		}
	
	
	
	@GetMapping(path="/{id}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<WorkflowResponse> getWorkflow(@PathVariable String id) {// Pathvariable c'est a dire que Id va
																			// recuperrer sa valeur depuis le segment
																			// dynamique
		WorkflowDto workflowDto = workflowService.getWorkflowByWorkflowId(id);
		ModelMapper modelMapper = new ModelMapper();
		WorkflowResponse workflowResponse= modelMapper.map(workflowDto, WorkflowResponse.class);
		// OK for Get ->200
		return new ResponseEntity<WorkflowResponse>(workflowResponse, HttpStatus.OK);

	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<WorkflowResponse> createWorkflow(@RequestBody @Valid WorkflowRequest workflowRequest)
			throws Exception {
		if (workflowRequest.getName().isEmpty())
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		ModelMapper modelMapper = new ModelMapper();
		WorkflowDto workflowDto = modelMapper.map(workflowRequest, WorkflowDto.class);
		WorkflowDto workflowCreate = workflowService.createWorkflow(workflowDto);
		WorkflowResponse workflowResponse = modelMapper.map(workflowCreate, WorkflowResponse.class);
// Created for Post ->201
		return new ResponseEntity<WorkflowResponse>(workflowResponse, HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteworkflow(@PathVariable String id) {
		workflowService.deleteWorkflow(id);
		// NO_CONTENT for delete 204
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@PutMapping(path = "/{workflowid}/{userid}",
			produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE })
			public ResponseEntity<WorkflowResponse> updateWorkflow(@PathVariable String workflowid,@PathVariable String userid) {

			ModelMapper modelMapper = new ModelMapper();

			WorkflowDto updateWorkflow = workflowService.updateWorkflow(workflowid, userid);
			WorkflowResponse workflowResponse =  modelMapper.map(updateWorkflow, WorkflowResponse.class);
			return new ResponseEntity<WorkflowResponse>(workflowResponse, HttpStatus.ACCEPTED);
			}
	
	@PutMapping(path = "/removing/{workflowid}/{userid}",
			produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE })
			public ResponseEntity<WorkflowResponse> updateWorkflowremove(@PathVariable String workflowid,@PathVariable String userid) {

			ModelMapper modelMapper = new ModelMapper();

			WorkflowDto updateWorkflow = workflowService.updateWorkflowRemove(workflowid, userid);
			WorkflowResponse workflowResponse =  modelMapper.map(updateWorkflow, WorkflowResponse.class);
			return new ResponseEntity<WorkflowResponse>(workflowResponse, HttpStatus.ACCEPTED);
			}

}
