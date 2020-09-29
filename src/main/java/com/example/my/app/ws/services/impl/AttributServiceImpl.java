package com.example.my.app.ws.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.my.app.ws.entities.AttributEntity;
import com.example.my.app.ws.entities.DataSetEntity;
import com.example.my.app.ws.repositories.AttributRepository;
import com.example.my.app.ws.repositories.DataSetRepository;
import com.example.my.app.ws.services.AttributService;
import com.example.my.app.ws.shared.Utils;
import com.example.my.app.ws.shared.dto.AttributDto;
import com.example.my.app.ws.shared.dto.DataSetDto;
import com.example.my.app.ws.shared.dto.ValueDto;

@Service
public class AttributServiceImpl implements AttributService {
	@Autowired
	AttributRepository attributRepository;
	@Autowired
	Utils util;
	
	@Autowired
	DataSetRepository datasetRepository; 
	

	@Override
	public AttributDto attributCreate(AttributDto attributDto,String id) {
		 DataSetEntity currentDataset = datasetRepository.findByDataSetId(id);
	
	/*	for (int i = 0; i < attributDto.getValues().size(); i++) {
			ValueDto value = attributDto.getValues().get(i);
			value.setAttribut(attributDto);
			value.setValueId(util.generateStringId(30));
			attributDto.getValues().set(i,value);
		}*/
		ModelMapper modelMapper = new ModelMapper();
		DataSetDto datasetDto = modelMapper.map(currentDataset, DataSetDto.class);
		
		//attributDto.getDatatype();
		attributDto.setDataset(datasetDto);
		attributDto.setAttributId(util.generateStringId(32));
		AttributEntity attributEntity = modelMapper.map(attributDto, AttributEntity.class);
		AttributEntity attributSave = attributRepository.save(attributEntity);
		AttributDto attribut = modelMapper.map(attributSave, AttributDto.class);
		return attribut;
	}





	@Override
	public void AttributDelete(String attributId) {
				AttributEntity attributEntity=attributRepository.findByAttributId(attributId);
				if(attributEntity ==null) throw  new UsernameNotFoundException(attributId);
				attributRepository.delete(attributEntity);

	}

	@Override
	public AttributDto createAttribut(AttributDto attribut, String id) {
		   DataSetEntity currentDataset = datasetRepository.findByDataSetId(id);
		
			ModelMapper modelMapper = new ModelMapper();
			DataSetDto datasetDto = modelMapper.map(currentDataset, DataSetDto.class);
			   
			   	for (int i = 0; i < datasetDto.getAttributs().size(); i++) {
			   		AttributDto att =  datasetDto.getAttributs().get(i);
			   		
				      if (att.getName().equals(attribut.getName())) {
				    	   
				    	  throw new RuntimeException("Attribut exist already");
				      }
			}
			  
			
			attribut.setAttributId(util.generateStringId(30));
			attribut.setDataset(datasetDto);
			DataSetEntity data=modelMapper.map(attribut.getDataset(), DataSetEntity.class);
			//AttributEntity attributEntity = modelMapper.map(attribut, AttributEntity.class); 
			AttributEntity attributEntity =new AttributEntity();
			attributEntity.setAttributId(attribut.getAttributId());
			
			attributEntity.setValues(null);
			attributEntity.setName(attribut.getName());
			
			attributEntity.setDatatype(attribut.getDatatype());
			attributEntity.setId(attribut.getId());
			attributEntity.setDataSet(data);
			
			AttributEntity newAttribut = attributRepository.save(attributEntity);
			
			AttributDto attributDto = modelMapper.map(newAttribut, AttributDto.class);
			
			return attributDto;
	}

	@Override
	public AttributDto getAttribute(String attributeId) {
		AttributEntity attributEntity = attributRepository.findByAttributId(attributeId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		AttributDto attributDto = modelMapper.map(attributEntity, AttributDto.class);
		
		return attributDto;
	}


	@Override
	public List<AttributDto> getAllAttributes(String datasetId) {

		DataSetEntity currentDataset = datasetRepository.findByDataSetId(datasetId);
		
		List<AttributEntity> attributes =  (List<AttributEntity>) attributRepository.findByDataSet(currentDataset);
		
		Type listType = new TypeToken<List<AttributDto>>() {}.getType();
		List<AttributDto> attributDto = new ModelMapper().map(attributes, listType);
		
		return attributDto;
	}





	@Override
	public AttributDto getAttributByAttributId(String attributId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
