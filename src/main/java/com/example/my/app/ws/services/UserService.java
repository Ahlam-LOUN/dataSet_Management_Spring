package com.example.my.app.ws.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.my.app.ws.entities.UserEntity;
import com.example.my.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto userCreate(UserDto userDto);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto userUpdate(String userId,UserDto userDto);
	void UserDelete(String userId);
	Page<UserEntity> getUsers(int page,int limit,String search,int status);
	//Page<UserEntity> getUsersInworkflow(String workflowId, int page, int limit, String search, int status);
	List<UserDto> getUsers(String workflowId);
	List<UserDto> getUsersHorsThiswork(String workflowId);
}
