package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.LeaveForm;
import com.klef.jfsd.springboot.repository.EmployeeRepository;
import com.klef.jfsd.springboot.repository.LeaveFormRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService 
{
	@Autowired 
	 private EmployeeRepository employeeRepository;
	
	@Autowired 
	 private LeaveFormRepository leaveFormRepository;

	@Override
	public Employee checkemplogin(String euname, String epwd) {
		return employeeRepository.checkemplogin(euname, epwd); 
	}

	@Override
	public String updateemployee(Employee emp) {
		Employee e = employeeRepository.findById(emp.getId()).get();    
	    
	      e.setName(emp.getName());
	      e.setDateofbirth(emp.getDateofbirth());
	      e.setDepartment(emp.getDepartment());
	      e.setSalary(emp.getSalary());
	      e.setEmail(emp.getEmail());
	      e.setPassword(emp.getPassword());
	      e.setLocation(emp.getLocation());
	      e.setContact(emp.getContact());
	    
	    employeeRepository.save(e);
	    
	    
	    return "Employee Updated Successfully";
	}



	@Override
	public Employee viewemployeebyid(int eid) 
	{
		//return employeeRepository.findById(eid).get();
		
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
	  public String addForm(LeaveForm form) {
	     leaveFormRepository.save(form);
	     return "LeaveForm";
	  }
	
	

	@Override
	public List<LeaveForm> viewreqh() {
		return leaveFormRepository.findAll();
	}


}
