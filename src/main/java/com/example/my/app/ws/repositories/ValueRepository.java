package com.example.my.app.ws.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.my.app.ws.entities.AttributEntity;
import com.example.my.app.ws.entities.ValueEntity;

@Repository
public interface ValueRepository  extends CrudRepository<ValueEntity,Long>{

	ValueEntity findByValueId(String valueId);

	List<ValueEntity> findAllByAttribut(AttributEntity currentAttribut);

}
