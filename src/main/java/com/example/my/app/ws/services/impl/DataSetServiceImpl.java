package com.example.my.app.ws.services.impl;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.my.app.ws.entities.AdressEntity;
import com.example.my.app.ws.entities.DataSetEntity;
import com.example.my.app.ws.entities.UserEntity;
import com.example.my.app.ws.entities.ValueEntity;
import com.example.my.app.ws.entities.WorflowEntity;
import com.example.my.app.ws.repositories.DataSetRepository;
import com.example.my.app.ws.repositories.UserRepository;
import com.example.my.app.ws.repositories.WorkflowRepository;
import com.example.my.app.ws.services.DataSetService;
import com.example.my.app.ws.shared.Utils;
import com.example.my.app.ws.shared.dto.AdressDto;
import com.example.my.app.ws.shared.dto.AttributDto;
import com.example.my.app.ws.shared.dto.DataSetDto;
import com.example.my.app.ws.shared.dto.UserDto;
import com.example.my.app.ws.shared.dto.ValueDto;

@Service
public class DataSetServiceImpl implements DataSetService {
	@Autowired
	DataSetRepository dataSetRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	WorkflowRepository   workflowRepository;
	@Autowired
	Utils util;

	@Override
	public DataSetDto dataSetCreate(DataSetDto dataSetDto) {
		DataSetEntity check = dataSetRepository.findByName(dataSetDto.getName());
		if (check != null)
			throw new RuntimeException("Dataset exist already");

	/*
	/*	for (int i = 0; i < dataSetDto.getAttributs().size(); i++) {
			
			AttributDto attributDto = dataSetDto.getAttributs().get(i);
			
			/*for (int j = 0; j < attributDto.getValues().size(); j++) {
				ValueDto value = attributDto.getValues().get(j);
				value.setAttribut(attributDto);
				value.setValueId(util.generateStringId(30));
				attributDto.getValues().set(j,value);
			}*/
			
			//	attributDto.setDataset(dataSetDto);
		//		attributDto.setAttributId(util.generateStringId(30));
			  //  dataSetDto.getAttributs().set(i, attributDto);
		//}
		
		ModelMapper modelMapper = new ModelMapper();
		DataSetEntity dataSetEntity = modelMapper.map(dataSetDto, DataSetEntity.class);
		dataSetEntity.setDataSetId(util.generateStringId(30));
		dataSetEntity.setLocation("location");
		DataSetEntity dataSave = dataSetRepository.save(dataSetEntity);
		DataSetDto dataDto = modelMapper.map(dataSave, DataSetDto.class);
		return dataDto;
	}

	@Override
	public DataSetDto getDataSet(String dataSetId) {
		DataSetEntity dataSetEntity= dataSetRepository.findByDataSetId(dataSetId);
		ModelMapper modelMapper = new ModelMapper();
		DataSetDto  dataSetDto=modelMapper.map(dataSetEntity,DataSetDto.class);
		return dataSetDto;
	
	}

	@Override
	public DataSetDto getDataSetByDataSetId(String dataSetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSetDto DataSetUpdate(String dataSetId, DataSetDto dataSetDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DataSetDelete(String dataSetId) {
		DataSetEntity dataSetEntity= dataSetRepository.findByDataSetId(dataSetId);
		if(dataSetEntity ==null) throw  new UsernameNotFoundException(dataSetId);
		dataSetRepository.delete(dataSetEntity);
	}

	@Override
	public List<DataSetDto> getAllDataSet() {
   
			List<DataSetEntity> datasets = (List<DataSetEntity>)dataSetRepository.findAll();
			Type listType = new TypeToken<List<DataSetDto>>() {}.getType();
			List<DataSetDto> datasetsDto=new  ModelMapper().map(datasets, listType);
			return datasetsDto;}
		
	

	@Override
	public DataSetDto updateDataset(String workflowid, String datasetid) {
		ModelMapper modelMapper = new ModelMapper();
		DataSetEntity datasetEntity = dataSetRepository.findByDataSetId(datasetid);
		if (datasetEntity == null)
			throw new RuntimeException("dataset doesn't exist");
		
		WorflowEntity workflowEntity = workflowRepository.findByWorkflowId(workflowid);
		if (workflowEntity == null)
			throw new RuntimeException("workflow doesn't exist");
		
		datasetEntity.setWorkflow(workflowEntity);
	         
		DataSetEntity datasetUpdated = dataSetRepository.save(datasetEntity);
		
		DataSetDto dataset = modelMapper.map(datasetUpdated, DataSetDto.class);
	
		return dataset;
	}

	@Override
	public List<DataSetDto> getDataSets(String id) {
		WorflowEntity dataSets= workflowRepository.findByWorkflowId(id);
		List<DataSetEntity> myDataSets=dataSets.getDatasets();
		Type listType = new TypeToken<List<DataSetDto>>() {}.getType();
		List<DataSetDto> dataDto=new  ModelMapper().map(myDataSets, listType);
		return dataDto;
	}

	@Override
	public List<DataSetDto> getDataSetsOut() {
		List<DataSetEntity> datasets = (List<DataSetEntity>)dataSetRepository.findAllDataByWorkflow();
		Type listType = new TypeToken<List<DataSetDto>>() {}.getType();
		List<DataSetDto> datasetsDto=new  ModelMapper().map(datasets, listType);
		return datasetsDto;
	}

	

}
