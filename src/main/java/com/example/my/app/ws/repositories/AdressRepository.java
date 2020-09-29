package com.example.my.app.ws.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.my.app.ws.entities.AdressEntity;
import com.example.my.app.ws.entities.UserEntity;
@Repository
public interface AdressRepository extends CrudRepository<AdressEntity, Long> {

	List<AdressEntity> findByUser(UserEntity currentUser);

	AdressEntity findByAdressId(String AdressId);

}
