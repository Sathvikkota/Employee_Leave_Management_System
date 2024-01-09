package com.klef.jfsd.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.LeaveForm;
import com.klef.jfsd.springboot.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("emp")
public class EmployeeController 
{
    @Autowired
   private EmployeeService employeeService;
   
   @GetMapping("/")
   @ResponseBody
   public String employee()
   {
	   return "Employee Microservice";
   }
   
   @GetMapping("home")
   public String main()
   {
	   return "main";
   }

   @GetMapping("viewreqhis")
	public ModelAndView viewreqh()
	{
		List<LeaveForm> reqlist = employeeService.viewreqh();
		
		ModelAndView mv=new ModelAndView("viewreqhis");
		mv.addObject("reqdata", reqlist);
		
		return mv;
	}
   
   
 
   
   @GetMapping("emphome")
	public ModelAndView emphome(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		
		int eid=(int)session.getAttribute("eid"); //eid is a session variable
		String ename=(String)session.getAttribute("ename"); //ename is a session variable
		
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("emphome");
	    
	    mv.addObject("eid", eid);
	    mv.addObject("ename", ename);
	    
	    return mv;
	}
   
   @GetMapping("emplogin")
	public ModelAndView emplogin()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("emplogin");
		return mv;
	}
	
	@PostMapping("checkemplogin")
	public ModelAndView checkemplogin(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		String email = request.getParameter("email");
	    String pwd = request.getParameter("pwd");
	     
	    Employee emp =  employeeService.checkemplogin(email, pwd);
	    
	    if(emp!=null && emp.isActive())
	    {
	    	//session
	    	
	    	HttpSession session=request.getSession();
	    	
	    	session.setAttribute("eid", emp.getId()); // eid is a session variable
	    	session.setAttribute("ename", emp.getName()); // ename is a session variable
	    	
	    	//session
	    	mv.setViewName("emphome");
	    }
	    else
	    {
	    	mv.setViewName("emplogin");
	    	mv.addObject("message", "Login Failed");
	    }
	    
	    return mv;

	}
   
   @GetMapping("updateempprofile")
	  public ModelAndView updateemp(HttpServletRequest request)
	  {
	    ModelAndView mv = new ModelAndView();
	    
	    HttpSession session = request.getSession();
	    
	    mv.setViewName("updateempprofile");
	    
	    mv.addObject("eid", session.getAttribute("eid"));
	    mv.addObject("ename", session.getAttribute("ename"));
	    
	    int id = (int) session.getAttribute("eid");
	    
	    Employee emp = employeeService.viewemployeebyid(id);
	    
	    mv.addObject("emp", emp);
	    
	    return mv;
	  }
   
   @PostMapping("updateemp")
	  public ModelAndView updateempaction(HttpServletRequest request)
	  {
	    String msg = null;
	    
	    ModelAndView mv = new ModelAndView();
	    
	      HttpSession session = request.getSession();
	    
	    mv.addObject("eid", session.getAttribute("eid"));
	    mv.addObject("ename", session.getAttribute("ename"));
	    
	    int id = (int) session.getAttribute("eid");
	    
	   try
	   {
	     String name = request.getParameter("name");
	     String dob = request.getParameter("dob");
	     String dept = request.getParameter("dept");
	     String email = request.getParameter("email");
	     String pwd = request.getParameter("pwd");
	     String location = request.getParameter("location");
	     String contact = request.getParameter("contact");
	     
	     Employee emp = new Employee();
	      emp.setId(id);
	      emp.setName(name);
	      emp.setDateofbirth(dob);
	      emp.setDepartment(dept);
	      emp.setEmail(email);
	      emp.setPassword(pwd);
	      emp.setLocation(location);
	      emp.setContact(contact);
	      
	      
	      msg = employeeService.updateemployee(emp);
	      
	      mv.setViewName("emplogin");
	      mv.addObject("message",msg);
	     
	   }
	   catch(Exception e)
	   {
	     msg = e.getMessage();
	     
	     mv.setViewName("updateerror");
	      mv.addObject("message",msg);
	   }
	    return mv;
	  }
   
   @GetMapping("emplogout")
	  public ModelAndView emplogout()
	  {
		  ModelAndView mv=new ModelAndView();
		  mv.setViewName("emplogin");
		  mv.addObject("message", "Logout Successfully");
		  return mv;
	  }
	  
   @GetMapping("leaverequestform")
	  public ModelAndView leavereq(HttpServletRequest req)
	  {
		  HttpSession session = req.getSession();
	      int eid = (int) session.getAttribute("eid");
	    
	    ModelAndView mv = new ModelAndView("leave_req");
	        mv.addObject("eid",eid);
	    return mv;
	  }
	  
	  @PostMapping("submitform")
	  public ModelAndView submit(HttpServletRequest request)
	  {
	     HttpSession session = request.getSession();
	      int eid = (int) session.getAttribute("eid");
	      String ename = (String) session.getAttribute("ename");
	      String email = (String) session.getAttribute("email");
	      
	    String msg=null;
	    
	    ModelAndView mv=new ModelAndView();
	    try
	    {
	      String name = request.getParameter("subject");
	        String st = request.getParameter("startDate");
	        String ed = request.getParameter("endDate");
	    
	        String reason = request.getParameter("reason");
	        LeaveForm lf=new LeaveForm();
	        
	        lf.setId(lf.getId());
	        lf.setSubject(name);
	        lf.setStartdate(st);
	        lf.setEnddate(ed);
	        lf.setEmpid(eid);
	        lf.setReason(reason);
	        lf.setStatus(LeaveForm.TriState.UNKNOWN);
	        
	        msg=employeeService.addForm(lf);
	        mv.setViewName("leave_req");
	      
	    }
	    catch(Exception e)
	    {
	      msg=e.getMessage();
	      mv.setViewName("displayerror");
	      mv.addObject("message",msg);
	    }
	    return mv;
	  
	  }
     
}
