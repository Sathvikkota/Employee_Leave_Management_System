package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.LeaveForm;
import com.klef.jfsd.springboot.model.Manager;
import com.klef.jfsd.springboot.repository.AdminRepository;
import com.klef.jfsd.springboot.repository.EmployeeRepository;
import com.klef.jfsd.springboot.repository.LeaveFormRepository;
import com.klef.jfsd.springboot.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService
{
	
	 @Autowired 
	 private AdminRepository adminRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private LeaveFormRepository leaveFormRepository;
	@Override
	public List<Employee> viewallemp() 
	{
		return employeeRepository.findAll();
	}

	@Override
	public Employee viewempmbyid(int eid) 
	{
		Optional<Employee> obj = employeeRepository.findById(eid);    
	    
	    if(obj.isPresent())
	    {
	      Employee emp = obj.get();
	      return emp;
	    }
	    else
	    {
	      return null;
	    }
	}

	@Override
	public Manager checkmnglogin(String muname, String mpwd) {
		return managerRepository.checkmnglogin(muname, mpwd);
	}

	@Override
	public String updatemanager(Manager mng) {
		Manager m = managerRepository.findById(mng.getId()).get();    
	    
	      m.setName(mng.getName());
	      m.setDateofbirth(mng.getDateofbirth());
	      m.setDepartment(mng.getDepartment());
	      m.setSalary(mng.getSalary());
	      m.setEmail(mng.getEmail());
	      m.setPassword(mng.getPassword());
	      m.setLocation(mng.getLocation());
	      m.setContact(mng.getContact());
	    
	    managerRepository.save(m);
	    
	    return "Manager Updated Successfully";
	}

	@Override
	public long empcount() {
		return employeeRepository.count();
	}

	

	@Override
	public int updateempstatusm(boolean active, int eid) {
		return managerRepository.updateempstatusm(active, eid);
	}

	@Override
	public Manager viewmanagerbyid(int mid) 
	{
		
		Optional<Manager> obj = managerRepository.findById(mid);    
	    
	    if(obj.isPresent())
	    {
	      Manager mng = obj.get();
	      return mng;
	    }
	    else
	    {
	      return null;
	    }

		
	}

	@Override
	public List<LeaveForm> viewallreqs() {
		return leaveFormRepository.findAll();
	}

	@Override
	public int setdecisionaction(LeaveForm.TriState status, int eid) {
		return managerRepository.setdecisionaction(status, eid);
	}

}
