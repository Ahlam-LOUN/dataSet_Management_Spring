package com.example.my.app.ws.services;

import java.util.List;


import com.example.my.app.ws.shared.dto.AdressDto;
public interface AdressService  {
 List<AdressDto> getAllAdresses(String email);

AdressDto adressCreate(AdressDto adressDto, String email);

void adressDelete(String AdressId);

AdressDto getAdress(String AdressId);
}
