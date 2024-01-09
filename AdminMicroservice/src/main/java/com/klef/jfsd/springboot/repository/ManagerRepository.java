package com.klef.jfsd.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.klef.jfsd.springboot.model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer>
{
	@Query("select m from Manager m where email=?1 and password=?2")
	public Manager checkmnglogin(String email,String pwd);
	
	
	
	@Query("update Employee set active=?1 where id=?2") 
	 @Modifying 
	 @Transactional 
	 public int updateempstatusm(boolean active,int eid);
	
	List<Manager> findByDepartment(String department);
}
