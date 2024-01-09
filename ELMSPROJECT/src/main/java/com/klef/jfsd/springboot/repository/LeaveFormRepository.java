package com.klef.jfsd.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.LeaveForm;

import com.klef.jfsd.springboot.model.Employee;

@Repository
public interface LeaveFormRepository extends JpaRepository<LeaveForm, Integer>
{
  
}


