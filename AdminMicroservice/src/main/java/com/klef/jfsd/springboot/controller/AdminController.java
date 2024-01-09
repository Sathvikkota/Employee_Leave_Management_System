package com.klef.jfsd.springboot.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.Manager;
import com.klef.jfsd.springboot.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminController 
{
   @Autowired	
   private AdminService adminService;
   
   @GetMapping("/")
   @ResponseBody
   public String admin()
   {
	   return "Admin Microservice";
   }
   
   @GetMapping("adminlogin") //URI & method name can be different
	public ModelAndView adminLogin()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("adminlogin");
		return mv;
	}
	
	@PostMapping("checkadminlogin")
	  public ModelAndView checkadminlogin(HttpServletRequest request)
	  {
	    ModelAndView mv = new ModelAndView(); 
	    
	    
	    String uname = request.getParameter("uname");
	     String pwd = request.getParameter("pwd");
	     
	    Admin admin = adminService.checkadminlogin(uname, pwd);
	    
	    if(admin!=null)
	    {
	      mv.setViewName("adminhome");
	      long ecount=adminService.empcount();
	      long mcount=adminService.mngcount();
	      mv.addObject("ecount", ecount);
	      mv.addObject("mcount", mcount);
	    }
	    else
	    {
	      mv.setViewName("adminlogin");
	      mv.addObject("message", "Login Failed");
	    }
	    
	    return mv;
	  }
   
	@GetMapping("empreg")
	public ModelAndView empregistration()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("empreg");
		return mv;
	}
	
	@PostMapping("insertemp")
	public ModelAndView insertactionemp(HttpServletRequest request,@RequestParam("profilepic") MultipartFile file)throws IOException, SerialException, SQLException
	{
		String msg=null;
		
		ModelAndView mv=new ModelAndView();
		
		try
		{
			String name = request.getParameter("name");
		    String gender = request.getParameter("gender");
		    String dob = request.getParameter("dob");
		    String dept = request.getParameter("dept");
		    String sal = request.getParameter("salary");
		    double salary = Double.parseDouble(sal);
		    String email = request.getParameter("email");
		    String pwd = request.getParameter("pwd");
		    String location = request.getParameter("location");
		    String contact = request.getParameter("contact");
		    byte[] bytes = file.getBytes();
			Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		    
		    Employee emp=new Employee();
		      emp.setName(name);
		      emp.setGender(gender);
		      emp.setDateofbirth(dob);
		      emp.setDepartment(dept);
		      emp.setSalary(salary);
		      emp.setEmail(email);
		      emp.setPassword(pwd);
		      emp.setLocation(location);
		      emp.setContact(contact);
		      emp.setActive(true);
		      emp.setProfilepic(blob);
		      
		      msg=adminService.addemployee(emp);
		      
		      mv.setViewName("displaymsg");
			  mv.addObject("message", msg); //msg-attribute
		}
		catch(Exception e)
		{
			msg=e.getMessage();
			mv.setViewName("displayerror");
			mv.addObject("message", msg); //msg-attribute
		}
		
		return mv;
	}
	
	@GetMapping("mngreg")
	public ModelAndView mngregistration()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("mngreg");
		return mv;
	}
	
	@PostMapping("insertmng")
	public ModelAndView insertactionmng(HttpServletRequest request,@RequestParam("profilepic") MultipartFile file)throws IOException, SerialException, SQLException
	{
		String msg=null;
		
		ModelAndView mv=new ModelAndView();
		
		try
		{
			String name = request.getParameter("name");
		    String gender = request.getParameter("gender");
		    String dob = request.getParameter("dob");
		    String dept = request.getParameter("dept");
		    String sal = request.getParameter("salary");
		    double salary = Double.parseDouble(sal);
		    String email = request.getParameter("email");
		    String pwd = request.getParameter("pwd");
		    String location = request.getParameter("location");
		    String contact = request.getParameter("contact");
		    byte[] bytes = file.getBytes();
			  Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		    
		    Manager mng=new Manager();
		      mng.setName(name);
		      mng.setGender(gender);
		      mng.setDateofbirth(dob);
		      mng.setDepartment(dept);
		      mng.setSalary(salary);
		      mng.setEmail(email);
		      mng.setPassword(pwd);
		      mng.setLocation(location);
		      mng.setContact(contact);
		      mng.setActive(true);
		      mng.setProfilepic(blob);
		      
		      msg=adminService.addmanager(mng);
		      
		      mv.setViewName("displaymsg");
			  mv.addObject("message", msg); //msg-attribute
		}
		catch(Exception e)
		{
			msg=e.getMessage();
			mv.setViewName("displayerror");
			mv.addObject("message", msg); //msg-attribute
		}
		
		return mv;
	}
	
	@GetMapping("viewemp")
	public ModelAndView viewempdemo(@RequestParam("id") int eid)
	{
	    Employee emp = adminService.viewempbyid(eid);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("viewempbyid");
	    mv.addObject("emp", emp);
	    return mv;
	}
	
	@RequestMapping(value = "viewallmngs", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewmngs(@RequestParam(name = "departmentFilter", required = false) String departmentFilter) {
        List<Manager> mnglist;

        if (departmentFilter != null && !departmentFilter.isEmpty()) {
            mnglist = adminService.viewMngsByDepartment(departmentFilter);
        } else {
            mnglist = adminService.viewallmngs();
        }

        ModelAndView mv = new ModelAndView("viewallmngs");
        mv.addObject("mngdata", mnglist);

        return mv;
    }
	
	@GetMapping("viewmng")
	public ModelAndView viewmngdemo(@RequestParam("id") int eid)
	{
	    Manager mng = adminService.viewmngbyid(eid);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("viewmngbyid");
	    mv.addObject("mng", mng);
	    return mv;
	}
	
	@RequestMapping(value = "viewallemps", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewemps(@RequestParam(name = "departmentFilter", required = false) String departmentFilter) {
        List<Employee> emplist;

        if (departmentFilter != null && !departmentFilter.isEmpty()) {
            emplist = adminService.viewEmpsByDepartment(departmentFilter);
        } else {
            emplist = adminService.viewallemps();
        }

        ModelAndView mv = new ModelAndView("viewallemps");
        mv.addObject("empdata", emplist);

        return mv;
    }
   
	@GetMapping("adminhome")
	public ModelAndView adminhome()
	{
		long ecount=adminService.empcount();
		long mcount=adminService.mngcount();
		
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("adminhome");
	    mv.addObject("ecount", ecount);
	    mv.addObject("mcount", mcount);
	    return mv;
	}
   
	@GetMapping("updateempstatus")
	  public ModelAndView updateempstatus()
	  {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("updateempstatus");
	    List<Employee> emplist =  adminService.viewallemps();
	    mv.addObject("empdata", emplist);
	    return mv;
	  }
	  
	  @GetMapping("updatemngstatus")
	  public ModelAndView updatemngstatus()
	  {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("updatemngstatus");
	    List<Manager> mnglist =  adminService.viewallmngs();
	    mv.addObject("mngdata", mnglist);
	    return mv;
	  }
   
	  @GetMapping("setempstatus")
	  public ModelAndView setempstatusaction(@RequestParam("id") int eid,@RequestParam("status") boolean status)
	  {
	    int n = adminService.updateempstatus(status, eid);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("updateempstatus");
	    List<Employee> emplist =  adminService.viewallemps();
	    mv.addObject("empdata", emplist);
	      
	   
	    if(n>0)
	    {
	      mv.addObject("message", "Status Updated Successfully");
	    }
	    else
	    {
	      mv.addObject("message", "Failed to Update Status");
	    }
	    
	    return mv;
	  }
	  
	  @GetMapping("setmngstatus")
	  public ModelAndView setmngstatusaction(@RequestParam("id") int mid,@RequestParam("status") boolean status)
	  {
	    int n = adminService.updatemngstatus(status, mid);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("updatemngstatus");
	    List<Manager> mnglist =  adminService.viewallmngs();
	    mv.addObject("mngdata", mnglist);
	      
	   
	    if(n>0)
	    {
	      mv.addObject("message", "Status Updated Successfully");
	    }
	    else
	    {
	      mv.addObject("message", "Failed to Update Status");
	    }
	    
	    return mv;
	  }
   
	  @GetMapping("displayprofilepic")
	   public ResponseEntity<byte[]> displayprofilepicdemo(@RequestParam("id") int id) throws IOException, SQLException
	   {
	     Manager mng =  adminService.viewmngbyid(id);
	     byte [] imageBytes = null;
	     imageBytes = mng.getProfilepic().getBytes(1,(int) mng.getProfilepic().length());

	     return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	   }

	   @GetMapping("displayeprofilepic")
	   public ResponseEntity<byte[]> displayeprofilepicdemo(@RequestParam("id") int id) throws IOException, SQLException
	   {
	     Employee emp =  adminService.viewempbyid(id);
	     byte [] imageBytes = null;
	     imageBytes = emp.getProfilepic().getBytes(1,(int) emp.getProfilepic().length());

	     return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	   }
     
}
