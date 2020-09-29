package com.example.my.app.ws.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.my.app.ws.entities.AdressEntity;
import com.example.my.app.ws.entities.UserEntity;
import com.example.my.app.ws.entities.ValueEntity;
import com.example.my.app.ws.entities.WorflowEntity;
import com.example.my.app.ws.repositories.UserRepository;
import com.example.my.app.ws.repositories.WorkflowRepository;
import com.example.my.app.ws.services.UserService;
import com.example.my.app.ws.shared.Utils;
import com.example.my.app.ws.shared.dto.AdressDto;
import com.example.my.app.ws.shared.dto.DataSetDto;
import com.example.my.app.ws.shared.dto.UserDto;
import com.example.my.app.ws.shared.dto.ValueDto;
import com.example.my.app.ws.shared.dto.WorkflowDto;

@Service
// service 'cause  this class implement userservice interface which is Autowired in usercontroller
public class UserServiceImpl implements UserService {
	@Autowired
    UserRepository userRepository;
	@Autowired
	WorkflowRepository workflowRepository;
	@Autowired
	Utils util;
	@Autowired 
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
////////////////////////
	/*@Override
	public UserDto userCreate(UserDto user) {
		UserEntity check=userRepository.findByEmail(user.getEmail());
		if(check!=null) throw new RuntimeException("user exist already");
        
        
        
        for(int i=0; i<user.getDatasets().size();i++) {
        	DataSetDto dataset=user.getDatasets().get(i);
        	//had set pour attacher l'address a un user
        	dataset.setUser(user);
        	dataset.setDataSetId(util.generateStringId(30));
        	user.getDatasets().set(i,dataset);
        }
        user.getContact().setContactId(util.generateStringId(30));
      //had set pour attacher un contact a un user
        user.getContact().setUser(user);
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity= modelMapper.map(user, UserEntity.class);
        
        
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(util.generateStringId(32));
		UserEntity userSave= userRepository.save(userEntity);
		UserDto userDto= modelMapper.map(userSave,UserDto.class);
		return userDto;
	}
*/
	@Override
	public UserDto userCreate(UserDto user) {
		UserEntity check=userRepository.findByEmail(user.getEmail());
		if(check!=null) throw new RuntimeException("user exist already");
        
        
        
      /*  for(int i=0; i<user.getAdresses().size();i++) {
        	AdressDto adress=user.getAdresses().get(i);
        	//had set pour attacher l'address a un user
        	adress.setUser(user);
        	adress.setAdressId(util.generateStringId(30));
        	user.getAdresses().set(i, adress);
        }
        user.getContact().setContactId(util.generateStringId(30));
      //had set pour attacher un contact a un user
        user.getContact().setUser(user);*/
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity= modelMapper.map(user, UserEntity.class);
        
        
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(util.generateStringId(32));
		UserEntity userSave= userRepository.save(userEntity);
		UserDto userDto= modelMapper.map(userSave,UserDto.class);
		return userDto;
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity=userRepository.findByEmail(email);
		if(userEntity ==null) throw  new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
	}
	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity=userRepository.findByEmail(email);
		if(userEntity ==null) throw  new UsernameNotFoundException(email);
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userEntity,userDto);
		return userDto;
	}
	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity=userRepository.findByUserId(userId);
		if(userEntity ==null) throw  new UsernameNotFoundException(userId);
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userEntity,userDto);
		return userDto;
	}
	@Override
	public UserDto userUpdate(String userId, UserDto userDto) {
		UserEntity userEntity=userRepository.findByUserId(userId);
		if(userEntity ==null) throw  new UsernameNotFoundException(userId);
		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLastname(userDto.getLastname());	
		if(bCryptPasswordEncoder.encode(userDto.getPassword())!=null) {
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));}
		
		UserEntity userUpdate= userRepository.save(userEntity);
		 ModelMapper modelMapper = new ModelMapper();
		 UserDto user= modelMapper.map(userUpdate, UserDto.class);
		return user;
	
	}
	@Override
	public void UserDelete(String userId) {
	
		UserEntity userEntity=userRepository.findByUserId(userId);
		if(userEntity ==null) throw  new UsernameNotFoundException(userId);
		userRepository.delete(userEntity);
	}
	
	/*@Override
	public Page<UserEntity> getUsersInworkflow(String workflowId,int page, int limit,String search,int status) {
	//public List<UserDto> getUsers(int page, int limit,String search,int status) {
		//if(page>0) page=page-1;
		List<UserDto> userDto=new ArrayList<>();
	     Pageable pageableRequest=PageRequest.of(page,limit);
		Page<UserEntity> userPage;
		if(search.isEmpty()) {
			 userPage= userRepository.findAllByWorkflow(workflowId,pageableRequest);
		}else {
		      userPage= userRepository.findAllUserByCriteriaWork(workflowId,pageableRequest,search,status);
		}	
		List<UserEntity> users= userPage.getContent();
		return userPage;
		
	}*/
	
	
	@Override
	public Page<UserEntity> getUsers(int page, int limit,String search,int status) {
	//public List<UserDto> getUsers(int page, int limit,String search,int status) {
		//if(page>0) page=page-1;
		List<UserDto> userDto=new ArrayList<>();
	     Pageable pageableRequest=PageRequest.of(page,limit);
		Page<UserEntity> userPage;
		if(search.isEmpty()) {
			 userPage= userRepository.findAllUsers(pageableRequest);
		}else {
		      userPage= userRepository.findAllUserByCriteria(pageableRequest,search,status);
		}	
		List<UserEntity> users= userPage.getContent();
		/*for(UserEntity userEntity:users) {
			
			ModelMapper modelMapper = new ModelMapper();
		    UserDto user = modelMapper.map(userEntity, UserDto.class);
			userDto.add(user);
		}*/
		//return userDto;
		return userPage;
		
	}
	
	@Override
	public List<UserDto> getUsers(String workflowId) {	
		WorflowEntity users= workflowRepository.findByWorkflowId(workflowId);
		List<UserEntity> myUsers=users.getUsers();
		Type listType = new TypeToken<List<UserDto>>() {}.getType();
		List<UserDto> userDto=new  ModelMapper().map(myUsers, listType);
		return userDto;
	}
	
	@Override
	public List<UserDto> getUsersHorsThiswork(String workflowId) {	
	WorflowEntity users= workflowRepository.findByWorkflowId(workflowId);
		List<UserEntity> usersOut =(List<UserEntity>) userRepository.findAll();
		
		//if(usersOut==null) throw new RuntimeException("Ce workflow est vide !!");

		List<UserEntity> myUsers=users.getUsers();
		 for(int i=0;i<usersOut.size();i++) {
			 for(int j=0;j<myUsers.size();j++) {
			 if(usersOut.get(i).getUserId().equals(myUsers.get(j).getUserId()))
				 usersOut.remove(usersOut.get(i));
			 }
		 }
		 Type listType = new TypeToken<List<UserDto>>() {}.getType();
			List<UserDto> userDto=new  ModelMapper().map(usersOut, listType);
			return userDto;
		
	     }
	//	
	


}
	

