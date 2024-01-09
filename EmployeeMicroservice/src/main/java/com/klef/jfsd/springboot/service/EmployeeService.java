package com.klef.jfsd.springboot.service;

import java.util.List;


import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.LeaveForm;

public interface EmployeeService 
{
	public Employee checkemplogin(String euname ,String epwd);
	public String updateemployee(Employee emp);
	public Employee viewemployeebyid(int eid);
	public String addForm(LeaveForm form);
	public List<LeaveForm> viewreqh();
}
