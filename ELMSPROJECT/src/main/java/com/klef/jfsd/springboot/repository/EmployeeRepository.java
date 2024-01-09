package com.klef.jfsd.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.Manager;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
	@Query("select e from Employee e where email=?1 and password=?2")
	public Employee checkemplogin(String email,String pwd);
	
	List<Employee> findByDepartment(String department);
}
