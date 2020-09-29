package com.example.my.app.ws.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.my.app.ws.entities.UserEntity;
import com.example.my.app.ws.entities.WorflowEntity;
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	@Query(value="select * from user",nativeQuery=true)
   	Page<UserEntity> findAllUsers(Pageable pageableRequest);
	
	// hada howa JPQL
	//@Query("SELECT users FROM UserEntity users")
	//Page<UserEntity> findAllUsers(Pageable pageableRequest);
	
	//L'oder important des param 
	//@Query(value="SELECT * FROM user u WHERE (u.firstname=?1 OR u.lastname=?1) AND u.emailverification_status=?2",nativeQuery=true)
	//Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest,String search,int status);

	//hna laa machi important hit lmes para hayweliw andhom des nomes
	@Query(value="SELECT * FROM user u WHERE (u.firstname LIKE %:search% OR u.lastname LIKE  %:search%) AND u.emailverification_status= :status",nativeQuery=true)
	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest,@Param("search") String search,@Param("status") int status);
	
	//List<UserEntity> findByWorkflowId(String workflowId);
	List<UserEntity> findByWorkflows(WorflowEntity workflow);
	
	
	
	
	
	
}
