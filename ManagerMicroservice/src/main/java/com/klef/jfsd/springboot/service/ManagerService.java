package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Manager;
import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.LeaveForm;

public interface ManagerService 
{

	public List<Employee> viewallemp();
	public Employee viewempmbyid(int eid);
	public Manager checkmnglogin(String muname ,String mpwd);
	public int updateempstatusm(boolean active, int eid);
	public String updatemanager(Manager mng);
	public Manager viewmanagerbyid(int mid);
	public long empcount();  //count(*)
	public List<LeaveForm> viewallreqs();
	public int setdecisionaction(LeaveForm.TriState status, int eid);

}
