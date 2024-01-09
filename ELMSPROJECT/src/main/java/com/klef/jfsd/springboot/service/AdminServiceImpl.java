package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.Manager;
import com.klef.jfsd.springboot.repository.AdminRepository;
import com.klef.jfsd.springboot.repository.EmployeeRepository;
import com.klef.jfsd.springboot.repository.ManagerRepository;

@Service
public class AdminServiceImpl implements AdminService
{

	 @Autowired 
	 private AdminRepository adminRepository; 
	  
	 @Autowired 
	 private EmployeeRepository employeeRepository; 
	  
	 @Autowired 
	 private ManagerRepository managerRepository;
	 
	 @Autowired 
	 private JavaMailSender javaMailSender;
	 
	 @Override 
	 public List<Employee> viewallemps() { 
	  return employeeRepository.findAll(); 
	 } 
	 
	 @Override 
	 public List<Manager> viewallmngs() { 
	  return managerRepository.findAll(); 
	 } 

	 @Override 
	 public Employee viewempbyid(int eid) { 
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
	 public Manager viewmngbyid(int mid) { 
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
	 public Admin checkadminlogin(String auname, String apwd) { 
	  return adminRepository.checkadminlogin(auname, apwd); 
	 } 

	@Override
	public int updateempstatus(boolean active, int eid) {
		return adminRepository.updateempstatus(active, eid);
	}

	@Override
	public int updatemngstatus(boolean active, int mid) {
		return adminRepository.updatemngpstatus(active, mid);
	}

	@Override 
	 public String addmanager(Manager mng) { 
	  managerRepository.save(mng); 
	  SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mng.getEmail());
		mailMessage.setSubject("Registration successful");
		mailMessage.setText("Welcome "+mng.getName()+"Username is"+mng.getEmail()+"One time Password is"+mng.getPassword());
		mailMessage.setFrom("lalitheshsathvik777@gmail.com");
		javaMailSender.send(mailMessage);		
	  return "Manager Registered Successfully"; 
	 } 
	 
	 @Override 
	 public String addemployee(Employee emp) { 
	  employeeRepository.save(emp); 
	  SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(emp.getEmail());
		mailMessage.setSubject("Registration successful");
		mailMessage.setText("Welcome "+emp.getName()+"Username is"+emp.getEmail()+"One time Password is"+emp.getPassword());
		mailMessage.setFrom("lalitheshsathvik777@gmail.com");
		javaMailSender.send(mailMessage);
		return "Employee Registered Successfully";
	 } 

	 @Override 
	 public long empcount() { 
	  return employeeRepository.count(); 
	 } 
	 
	 @Override 
	 public long mngcount() { 
	  return managerRepository.count(); 
	 }

	@Override
	public List<Manager> viewMngsByDepartment(String department) {
		return managerRepository.findByDepartment(department);
		
	} 
	@Override
	public List<Employee> viewEmpsByDepartment(String department) {
		return employeeRepository.findByDepartment(department);
		
	} 
	
}
