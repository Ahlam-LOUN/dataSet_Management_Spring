package com.example.my.app.ws.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.my.app.ws.entities.AdressEntity;
import com.example.my.app.ws.entities.DataSetEntity;
import com.example.my.app.ws.entities.UserEntity;
import com.example.my.app.ws.entities.WorflowEntity;
import com.example.my.app.ws.repositories.UserRepository;
import com.example.my.app.ws.repositories.WorkflowRepository;
import com.example.my.app.ws.services.WorkflowService;
import com.example.my.app.ws.shared.Utils;
import com.example.my.app.ws.shared.dto.AdressDto;
import com.example.my.app.ws.shared.dto.AttributDto;
import com.example.my.app.ws.shared.dto.DataSetDto;
import com.example.my.app.ws.shared.dto.UserDto;
import com.example.my.app.ws.shared.dto.ValueDto;
import com.example.my.app.ws.shared.dto.WorkflowDto;

@Service
public class WorkflowServiceImpl implements WorkflowService {
	@Autowired
	WorkflowRepository workflowRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils util;

	public WorkflowDto createWorkflow(WorkflowDto workflowDto) {
		WorflowEntity check = workflowRepository.findByName(workflowDto.getName());
		if(check!=null) throw new RuntimeException("workflow exist already");
		ModelMapper modelMapper = new ModelMapper();
		WorflowEntity workflowEntity = modelMapper.map(workflowDto, WorflowEntity.class);
		workflowEntity.setWorkflowId(util.generateStringId(30));
		WorflowEntity workflowSave = workflowRepository.save(workflowEntity);
		WorkflowDto workflowdto = modelMapper.map(workflowSave, WorkflowDto.class);
		return workflowdto;
	}
	
	
	@Override
	public WorkflowDto getWorkflowByWorkflowId(String workflowId) {
		WorflowEntity workflowSave = workflowRepository.findByWorkflowId(workflowId);
		if(workflowSave  ==null) throw  new UsernameNotFoundException(workflowId);
		ModelMapper modelMapper = new ModelMapper();
		WorkflowDto workflowdto = modelMapper.map(workflowSave, WorkflowDto.class);
		
		return workflowdto;
	}
	
	@Override
	public void deleteWorkflow(String workflowId) {
		WorflowEntity workflowSave = workflowRepository.findByWorkflowId(workflowId);
		if(workflowSave  ==null) throw  new UsernameNotFoundException(workflowId);
		workflowRepository.delete(workflowSave);
	}
	
	
	@Override
	public List<WorkflowDto> getAllworkflows(String email) {
		UserEntity currentUser=userRepository.findByEmail(email);
		List<WorflowEntity> workflows =currentUser.getAdmin()==true ? (List<WorflowEntity>)workflowRepository.findAll():(List<WorflowEntity>)workflowRepository.findByUsers(currentUser);
		//ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<WorkflowDto>>() {}.getType();
		List<WorkflowDto>  workflowDto=new  ModelMapper().map(workflows, listType);
		return workflowDto;
	}


	@Override
	public WorkflowDto updateWorkflow(String workflowid, String userid) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepository.findByUserId(userid);
		if (userEntity == null)
			throw new RuntimeException("dataset doesn't exist");
		
		WorflowEntity workflowEntity = workflowRepository.findByWorkflowId(workflowid);
		if (workflowEntity == null)
			throw new RuntimeException("workflow doesn't exist");
		
		if(workflowEntity.getUsers().contains(userEntity))
			throw new RuntimeException("user already added");
		
		workflowEntity.getUsers().add(userEntity);
		
	         
		WorflowEntity workflowUpdated = workflowRepository.save(workflowEntity);
		
		WorkflowDto workflow = modelMapper.map(workflowUpdated, WorkflowDto.class);
	
		return workflow;
	}
	
	
	
	@Override
	public WorkflowDto updateWorkflowRemove(String workflowid, String userid) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepository.findByUserId(userid);
		if (userEntity == null)
			throw new RuntimeException("dataset doesn't exist");
		
		WorflowEntity workflowEntity = workflowRepository.findByWorkflowId(workflowid);
		if (workflowEntity == null)
			throw new RuntimeException("workflow doesn't exist");
		
		if(!workflowEntity.getUsers().contains(userEntity))
			throw new RuntimeException("user already doesn't exist ");
		
		workflowEntity.getUsers().remove(userEntity);
		
	         
		WorflowEntity workflowUpdated = workflowRepository.save(workflowEntity);
		
		WorkflowDto workflow = modelMapper.map(workflowUpdated, WorkflowDto.class);
	
		return workflow;
	}
	
	
	
	
	
}
