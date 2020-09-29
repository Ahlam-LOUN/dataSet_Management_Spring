package com.example.my.app.ws.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.my.app.ws.entities.AdressEntity;
import com.example.my.app.ws.entities.AttributEntity;
import com.example.my.app.ws.entities.UserEntity;
import com.example.my.app.ws.entities.ValueEntity;
import com.example.my.app.ws.repositories.AttributRepository;
import com.example.my.app.ws.repositories.ValueRepository;
import com.example.my.app.ws.services.ValueService;
import com.example.my.app.ws.shared.Utils;
import com.example.my.app.ws.shared.dto.AdressDto;
import com.example.my.app.ws.shared.dto.AttributDto;
import com.example.my.app.ws.shared.dto.UserDto;
import com.example.my.app.ws.shared.dto.ValueDto;

@Service
public class ValueServiceImpl implements ValueService {

	@Autowired
	ValueRepository valueRepository; 
	@Autowired
	AttributRepository attributRepository;
	@Autowired
	Utils util;
	
	@Override
	public ValueDto  valueCreate(ValueDto valueDto,String id) {
		
		
		 AttributEntity attribut = attributRepository.findByAttributId(id);
			
		ModelMapper modelMapper = new ModelMapper();
		AttributDto attributDto = modelMapper.map(attribut,AttributDto.class);
		valueDto.setAttribut(attributDto);
		valueDto.setValueId(util.generateStringId(32));
		
		//ValueEntity valueEntity = modelMapper.map(valueDto, ValueEntity.class);
		ValueEntity valueEntity=new ValueEntity();
		valueEntity.setValue(valueDto.getValue());
		valueEntity.setValueId(valueDto.getValueId());
		valueEntity.setId(valueDto.getId());
		valueEntity.setAttribut( attribut);
		ValueEntity valueSave = valueRepository.save(valueEntity);
		ValueDto value = modelMapper.map(valueSave, ValueDto.class);
		return value;
	}

	@Override
	public ValueDto getValue(String valueId) {
		ValueEntity valueEntity= valueRepository.findByValueId(valueId);
		ModelMapper modelMapper = new ModelMapper();
        ValueDto  valueDto=modelMapper.map(valueEntity,ValueDto.class);
		return valueDto;
	}

	

	@Override
	public ValueDto valueUpdate(String valueId, ValueDto valueDto) {
		ValueEntity valueEntity= valueRepository.findByValueId(valueId);
		valueEntity= valueRepository.findByValueId(valueId);
		if(valueEntity ==null)  throw  new UsernameNotFoundException(valueId);
		valueEntity.setValue(valueDto.getValue());	
		ValueEntity valueUpdate=  valueRepository.save(valueEntity);
		ModelMapper modelMapper = new ModelMapper();
		ValueDto valueUpdated= modelMapper.map(valueUpdate, ValueDto.class);
		return valueUpdated;
	}

	@Override
	public void valueDelete(String valueId) {
		ValueEntity valueEntity=valueRepository.findByValueId(valueId);
		if(valueEntity ==null) throw  new UsernameNotFoundException( valueId);
		valueRepository.delete(valueEntity);

	}

	@Override
	public List<ValueDto> getAllValue(String attributId) {
		AttributEntity currentAttribut=attributRepository.findByAttributId(attributId);
		List<ValueEntity> values= (List<ValueEntity>) valueRepository.findAllByAttribut(currentAttribut);
		//ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<ValueDto>>() {}.getType();
		List<ValueDto> valueDto=new  ModelMapper().map(values, listType);
		return valueDto;
		
	}

	
}
