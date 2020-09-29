package com.example.my.app.ws.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.my.app.ws.entities.AttributEntity;
import com.example.my.app.ws.entities.DataSetEntity;

@Repository
public interface AttributRepository extends CrudRepository<AttributEntity,Long>{

	AttributEntity findByAttributId(String attributId);

	AttributEntity findByName(String name);

	List<AttributEntity> findByDataSet(DataSetEntity currentDataset);
	

}
