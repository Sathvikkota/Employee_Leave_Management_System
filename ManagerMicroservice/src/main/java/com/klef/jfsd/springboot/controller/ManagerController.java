package com.klef.jfsd.springboot.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.model.LeaveForm;
import com.klef.jfsd.springboot.model.Manager;
import com.klef.jfsd.springboot.service.ManagerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("man")
public class ManagerController 
{

	@Autowired
	private ManagerService managerService;
	
	@GetMapping("/")
	   @ResponseBody
	   public String employee()
	   {
		   return "Manager Microservice";
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
	@GetMapping("viewallemp")
	public ModelAndView viewemp()
	{
		List<Employee> emplist = managerService.viewallemp();
		
		ModelAndView mv=new ModelAndView("viewallemp");
		mv.addObject("empdata", emplist);
		
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
	@GetMapping("viewallreqs")
	public ModelAndView viewreqs()
	{
		List<LeaveForm> reqlist = managerService.viewallreqs();
		
		ModelAndView mv=new ModelAndView("viewallreqs");
		mv.addObject("reqdata", reqlist);
		
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
	  @GetMapping("viewmngprofile")
		public ModelAndView viewManagerProfile(HttpSession session) {
		    ModelAndView mv = new ModelAndView();
		    Integer managerId = (Integer) session.getAttribute("mid");

		        Manager mng = managerService.viewmanagerbyid(managerId);
		        mv.setViewName("viewmngprofile");
		        mv.addObject("mng", mng);
		    return mv;
		}
	  @GetMapping("displayeprofilepic")
	  public ResponseEntity<byte[]> displayeprofilepicdemo(@RequestParam("id") int id) throws IOException, SQLException
	  {
	    Employee emp =  managerService.viewempmbyid(id);
	    byte [] imageBytes = null;
	    imageBytes = emp.getProfilepic().getBytes(1,(int) emp.getProfilepic().length());

	    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	  }
}