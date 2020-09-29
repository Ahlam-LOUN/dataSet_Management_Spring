package com.example.my.app.ws.controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.my.app.ws.entities.UserEntity;
import com.example.my.app.ws.exceptions.UserException;
import com.example.my.app.ws.requests.UserRequest;
import com.example.my.app.ws.responses.ErrorMessages;
import com.example.my.app.ws.responses.UserResponse;
import com.example.my.app.ws.responses.ValueResponse;
import com.example.my.app.ws.services.UserService;
import com.example.my.app.ws.shared.dto.UserDto;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/user") // localhost:8080/user
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(path="/{id}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {// Pathvariable c'est a dire que Id va
																			// recuperrer sa valeur depuis le segment
																			// dynamique
		UserDto userDto = userService.getUserByUserId(id);
		ModelMapper modelMapper = new ModelMapper();
		UserResponse userResponse= modelMapper.map(userDto, UserResponse.class);
		// OK for Get ->200
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);

	}
	@GetMapping(path="/users-out/{id}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<UserResponse>> getUserOutWorkflow(@PathVariable String id) {// Pathvariable c'est a dire que Id va
																			// recuperrer sa valeur depuis le segment
																			// dynamique
		List<UserDto>  userDto = userService.getUsersHorsThiswork(id);
		Type listType = new TypeToken<List<UserResponse>>() {}.getType();
		List<UserResponse> userResponse =new ModelMapper().map(userDto, listType);
		// OK for Get ->200
		return new ResponseEntity<List<UserResponse>>(userResponse, HttpStatus.OK);

	}
	@GetMapping(path="/workflow-manage/{id}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<UserResponse>> getUsersInworkflow(@PathVariable String id) {// Pathvariable c'est a dire que Id va
																			// recuperrer sa valeur depuis le segment
																			// dynamique
		List<UserDto> userDto = userService.getUsers(id);
		Type listType = new TypeToken<List<UserResponse>>() {}.getType();
		List<UserResponse> userResponse = new ModelMapper().map(userDto, listType);
		// OK for Get ->200
		return new ResponseEntity<List<UserResponse>>(userResponse, HttpStatus.OK);

	}
	
	
	@GetMapping( produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Page<UserResponse> getAllUsers(@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="limit",defaultValue="7") int limit,@RequestParam(value="search",defaultValue="") String search,@RequestParam(value="status",defaultValue="0") int status){
		List<UserResponse> usersResponse=new ArrayList<>();
		Page<UserEntity> pageUsers=userService.getUsers(page,limit,search,status);
		 Pageable pageableRequest=PageRequest.of(page,limit);
		ModelMapper modelMapper = new ModelMapper();
		List<UserResponse> todos = pageUsers
				.stream()
				 .map(user -> modelMapper.map(user, UserResponse.class))
				 .collect(Collectors.toList());
		return new PageImpl<>(todos, pageableRequest, pageUsers.getTotalElements());

		/*for(UserDto userDto:users) {
			
			
			ModelMapper modelMapper = new ModelMapper();
			UserResponse user= modelMapper.map(userDto, UserResponse.class);
			usersResponse.add(user);
		}*/
		//return usersResponse;
	}
	/*
	@GetMapping(path="/{workflowId}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Page<UserResponse> getAllUsersbyworkflow(@PathVariable String workflowId,@RequestParam(value="page",defaultValue="1") int page,@RequestParam(value="limit",defaultValue="7") int limit,@RequestParam(value="search",defaultValue="") String search,@RequestParam(value="status",defaultValue="0") int status){
		List<UserResponse> usersResponse=new ArrayList<>();
		Page<UserEntity> pageUsers=userService.getUsersInworkflow(workflowId, page, limit, search, status);
		 Pageable pageableRequest=PageRequest.of(page,limit);
		ModelMapper modelMapper = new ModelMapper();
		List<UserResponse> todos = pageUsers
				.stream()
				 .map(user -> modelMapper.map(user, UserResponse.class))
				 .collect(Collectors.toList());
		return new PageImpl<>(todos, pageableRequest, pageUsers.getTotalElements());

		/*for(UserDto userDto:users) {
			
			
			ModelMapper modelMapper = new ModelMapper();
			UserResponse user= modelMapper.map(userDto, UserResponse.class);
			usersResponse.add(user);
		}*/
		//return usersResponse;
//	}*/


////////////////////////

	@PostMapping(consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			     produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
		if(userRequest.getFirstname().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
	    UserDto userCreate = userService.userCreate(userDto);
		UserResponse userResponse = modelMapper.map(userCreate, UserResponse.class);
		// Created for Post ->201
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	
	
	
	
	
	@PutMapping(path="/{id}",
			    consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
		        produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		// Presentation Layer
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto= modelMapper.map(userRequest, UserDto.class);
		
		// Server Layer
		UserDto userUpdate = userService.userUpdate(id, userDto);
		ModelMapper modelMappe = new ModelMapper();
		UserResponse userResponse= modelMappe.map(userUpdate, UserResponse.class);
		// Accepted for Put ->202
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.UserDelete(id);
		// NO_CONTENT for delete 204
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	


}
