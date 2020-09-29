package com.example.my.app.ws.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.my.app.ws.entities.UserEntity;
import com.example.my.app.ws.entities.WorflowEntity;
@Repository
public interface WorkflowRepository extends CrudRepository<WorflowEntity,Long> {

	WorflowEntity findByName(String name);

	WorflowEntity findByWorkflowId(String workflowId);

	List<WorflowEntity> findByUsers(UserEntity currentUser);

}
