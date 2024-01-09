package com.klef.jfsd.springboot.service;

import java.util.List;


import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Manager;
import com.klef.jfsd.springboot.model.Employee;

public interface AdminService 
{
	public List<Employee> viewallemps();
	public List<Manager> viewallmngs();
	public List<Manager> viewMngsByDepartment(String department);
	public List<Employee> viewEmpsByDepartment(String department);
	public Employee viewempbyid(int eid);
	public Manager viewmngbyid(int mid);
	public Admin checkadminlogin(String auname ,String apwd);
	public int updateempstatus(boolean active, int eid);
	public int updatemngstatus(boolean active, int mid);
	public String addmanager(Manager m);
	public String addemployee(Employee e);
	public long empcount();  //count(*)
	public long mngcount();  //count	
	
}
