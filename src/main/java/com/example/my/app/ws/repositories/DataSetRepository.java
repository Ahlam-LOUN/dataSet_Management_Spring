package com.example.my.app.ws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.my.app.ws.entities.DataSetEntity;


@Repository
public interface DataSetRepository extends CrudRepository<DataSetEntity,Long>{
	DataSetEntity findByName(String name);
	DataSetEntity findByDataSetId(String id);
	@Query(value="SELECT * FROM datasets d WHERE d.workflow_id is NULL",nativeQuery=true)
	List<DataSetEntity> findAllDataByWorkflow();
}
