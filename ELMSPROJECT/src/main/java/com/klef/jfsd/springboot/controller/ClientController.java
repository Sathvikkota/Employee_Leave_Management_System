package com.klef.jfsd.springboot.controller;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Manager;
import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.LeaveForm;
import com.klef.jfsd.springboot.service.AdminService;
import com.klef.jfsd.springboot.service.EmployeeService;
import com.klef.jfsd.springboot.service.ManagerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Controller
public class ClientController 
{
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ManagerService managerService;
	
	@GetMapping("/")
	public String main()
	{
		return "index";
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

	
	@GetMapping("emplogin")
	public ModelAndView emplogin()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("emplogin");
		return mv;
	}
	
	@PostMapping("checkemplogin")
	public ModelAndView checkemplogin(HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();

	    String email = request.getParameter("email");
	    String pwd = request.getParameter("pwd");

	    Employee emp = employeeService.checkemplogin(email, pwd);

	    if (emp != null && emp.isActive()) {
	        // Session
	        HttpSession session = request.getSession();

	        session.setAttribute("eid", emp.getId()); // eid is a session variable
	        session.setAttribute("ename", emp.getName()); // ename is a session variable

	        // Session
	        mv.setViewName("emphome");
	    } else {
	        mv.setViewName("emplogin");
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
	
	@GetMapping("viewallemp")
	public ModelAndView viewemp()
	{
		List<Employee> emplist = managerService.viewallemp();
		
		ModelAndView mv=new ModelAndView("viewallemp");
		mv.addObject("empdata", emplist);
		
		return mv;
	}
	
	@GetMapping("viewallreqs")
	public ModelAndView viewreqs()
	{
		List<LeaveForm> reqlist = managerService.viewallreqs();
		
		ModelAndView mv=new ModelAndView("viewallreqs");
		mv.addObject("reqdata", reqlist);
		
		return mv;
	}

	
	@GetMapping("viewreqhis")
	public ModelAndView viewreqh()
	{
		List<LeaveForm> reqlist = employeeService.viewreqh();
		
		ModelAndView mv=new ModelAndView("viewreqhis");
		mv.addObject("reqdata", reqlist);
		
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
	
	@GetMapping("viewempm")
	public ModelAndView viewempmdemo(@RequestParam("id") int eid)
	{
	    Employee emp = managerService.viewempmbyid(eid);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("viewempmbyid");
	    mv.addObject("emp", emp);
	    return mv;
	}
	
	@GetMapping("mnglogin")
	public ModelAndView mnglogin()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("mnglogin");
		return mv;
	}
	
	@PostMapping("checkmnglogin")
	public ModelAndView checkmnglogin(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		
		String email = request.getParameter("email");
	    String pwd = request.getParameter("pwd");
	     
	    Manager mng =  managerService.checkmnglogin(email, pwd);
	    
	    if(mng!=null && mng.isActive())
	    {
	    	//session
	    	
	    	HttpSession session=request.getSession();
	    	
	    	session.setAttribute("mid", mng.getId()); // mid is a session variable
	    	session.setAttribute("mname", mng.getName()); // mname is a session variable
	    	
	    	//session
	    	mv.setViewName("mnghome");
	    }
	    else
	    {
	    	mv.setViewName("mnglogin");
	    	mv.addObject("message", "Login Failed");
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
	
	@GetMapping("viewmngprofile")
	public ModelAndView viewManagerProfile(HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    Integer managerId = (Integer) session.getAttribute("mid");

	        Manager mng = adminService.viewmngbyid(managerId);
	        mv.setViewName("viewmngprofile");
	        mv.addObject("mng", mng);
	    return mv;
	}
	
	@GetMapping("viewempprofile")
	public ModelAndView viewEmployeeProfile(HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    Integer employeeId = (Integer) session.getAttribute("eid");

	        Employee emp = adminService.viewempbyid(employeeId);
	        mv.setViewName("viewempprofile");
	        mv.addObject("emp", emp);
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
	
	@GetMapping("mnghome")
	public ModelAndView mnghome(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		
		int mid=(int)session.getAttribute("mid"); //mid is a session variable
		String mname=(String)session.getAttribute("mname"); //mname is a session variable
		
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("mnghome");
	    
	    mv.addObject("mid", mid);
	    mv.addObject("mname", mname);
	    
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
	
	@GetMapping("updatemngprofile")
	  public ModelAndView updatemng(HttpServletRequest request)
	  {
	    ModelAndView mv = new ModelAndView();
	    
	    HttpSession session = request.getSession();
	    
	    mv.setViewName("updatemngprofile");
	    
	    mv.addObject("mid", session.getAttribute("mid"));
	    mv.addObject("mname", session.getAttribute("mname"));
	    
	    int id = (int) session.getAttribute("mid");
	    
	    Manager mng = managerService.viewmanagerbyid(id);
	    
	    mv.addObject("mng", mng);
	    
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
	  
	  @PostMapping("updatemng")
	  public ModelAndView updatemngaction(HttpServletRequest request)
	  {
	    String msg = null;
	    
	    ModelAndView mv = new ModelAndView();
	    
	      HttpSession session = request.getSession();
	    
	    mv.addObject("mid", session.getAttribute("mid"));
	    mv.addObject("mname", session.getAttribute("mname"));
	    
	    int id = (int) session.getAttribute("mid");
	    
	   try
	   {
		   String name = request.getParameter("name");
		     String dob = request.getParameter("dob");
		     String dept = request.getParameter("dept");
		     String email = request.getParameter("email");
		     String pwd = request.getParameter("pwd");
		     String location = request.getParameter("location");
		     String contact = request.getParameter("contact");
	     
	     Manager mng = new Manager();
	      mng.setId(id);
	      mng.setName(name);
	      mng.setDateofbirth(dob);
	      mng.setDepartment(dept);
	      mng.setEmail(email);
	      mng.setPassword(pwd);
	      mng.setLocation(location);
	      mng.setContact(contact);
	      
	      
	      msg = managerService.updatemanager(mng);
	      
	      mv.setViewName("mnglogin");
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
	  
	  @GetMapping("setdecisionaction")
	  public ModelAndView updatestatus()
	  {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("setdecisionaction");
	    List<LeaveForm> leavelist =  managerService.viewallreqs();
	    mv.addObject("reqdata", leavelist);
	    return mv;
	  }
	  
	  @GetMapping("setstatus")
	  public ModelAndView setstatusaction(@RequestParam("id") int eid, @RequestParam("status") LeaveForm.TriState status) {
	      int n = managerService.setdecisionaction(status, eid);

	      ModelAndView mv = new ModelAndView();
	      mv.setViewName("setdecisionaction");
	      List<LeaveForm> leavelist = managerService.viewallreqs();
	      mv.addObject("reqdata", leavelist);

	      
	      if (n > 0) {
	          mv.addObject("message", "Status Updated Successfully");
	      } else {
	          mv.addObject("message", "Failed to Update Status");
	      }

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
	  
	  @GetMapping("updateempstatusm")
	  public ModelAndView updateempstatusm()
	  {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("updateempstatusm");
	    List<Employee> emplist =  managerService.viewallemp();
	    mv.addObject("empdata", emplist);
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
	  
	  @GetMapping("setempstatusm")
	  public ModelAndView setempstatusactionm(@RequestParam("id") int eid,@RequestParam("status") boolean status)
	  {
	    int n = managerService.updateempstatusm(status, eid);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("updateempstatusm");
	    List<Employee> emplist =  managerService.viewallemp();
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

	  @GetMapping("emplogout")
	  public ModelAndView emplogout()
	  {
		  ModelAndView mv=new ModelAndView();
		  mv.setViewName("emplogin");
		  mv.addObject("message", "Logout Successfully");
		  return mv;
	  }
	  
	  @GetMapping("mnglogout")
	  public ModelAndView mnglogout()
	  {
		  ModelAndView mv=new ModelAndView();
		  mv.setViewName("mnglogin");
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
	  
	  @GetMapping("displayprofilepic")
	  public ResponseEntity<byte[]> displayprofilepicdemo(@RequestParam("id") int id) throws IOException, SQLException
	  {
	    Manager mng =  managerService.viewmanagerbyid(id);
	    byte [] imageBytes = null;
	    imageBytes = mng.getProfilepic().getBytes(1,(int) mng.getProfilepic().length());

	    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	  }
	  
	  @GetMapping("displayeprofilepic")
	  public ResponseEntity<byte[]> displayeprofilepicdemo(@RequestParam("id") int id) throws IOException, SQLException
	  {
	    Employee emp =  employeeService.viewemployeebyid(id);
	    byte [] imageBytes = null;
	    imageBytes = emp.getProfilepic().getBytes(1,(int) emp.getProfilepic().length());

	    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	  }
	  
	  
	  

}
