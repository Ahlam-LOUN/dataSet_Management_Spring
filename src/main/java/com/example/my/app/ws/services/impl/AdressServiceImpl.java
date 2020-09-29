package com.example.my.app.ws.services.impl;


import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.my.app.ws.entities.AdressEntity;
import com.example.my.app.ws.entities.UserEntity;
import com.example.my.app.ws.repositories.AdressRepository;
import com.example.my.app.ws.repositories.UserRepository;
import com.example.my.app.ws.services.AdressService;
import com.example.my.app.ws.shared.Utils;
import com.example.my.app.ws.shared.dto.AdressDto;
import com.example.my.app.ws.shared.dto.UserDto;
@Service

public class AdressServiceImpl implements  AdressService {
	@Autowired
	AdressRepository adressRepository;
	@Autowired
	UserRepository userRepository;
 
	@Autowired
	Utils util;
	@Override
	public List<AdressDto> getAllAdresses(String email) {
		UserEntity currentUser=userRepository.findByEmail(email);
		List<AdressEntity> adresses =currentUser.getAdmin()==true ? (List<AdressEntity>)adressRepository.findAll():(List<AdressEntity>)adressRepository.findByUser(currentUser);
		//ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<AdressDto>>() {}.getType();
		List<AdressDto> adressesDto=new  ModelMapper().map(adresses, listType);
		return adressesDto;
	}
	@Override
	public AdressDto adressCreate(AdressDto adress, String email) {
			UserEntity currentUser=userRepository.findByEmail(email);	        
	        ModelMapper modelMapper = new ModelMapper();
	        UserDto  userDto= modelMapper.map(currentUser, UserDto.class);
	        adress.setAdressId(util.generateStringId(32));
	        adress.setUser(userDto);
	        AdressEntity adressEntity= modelMapper.map(adress,AdressEntity.class);
	        AdressEntity adressSave= adressRepository.save(adressEntity);
	        AdressDto adressDto= modelMapper.map(adressSave,AdressDto.class);
			return adressDto;
	
	}
	@Override
	public void adressDelete(String AdressId) {
			// TODO Auto-generated method stub
			AdressEntity adressEntity=adressRepository.findByAdressId(AdressId);
			if( adressEntity ==null) throw  new RuntimeException("Address not found!!!");
			adressRepository.delete(adressEntity);	
	}
	@Override
	public AdressDto getAdress(String id) {
		AdressEntity adressEntity=adressRepository.findByAdressId(id);
		ModelMapper modelMapper = new ModelMapper();
        AdressDto  adressDto=modelMapper.map(adressEntity,AdressDto.class);
		return adressDto;
	}

}
