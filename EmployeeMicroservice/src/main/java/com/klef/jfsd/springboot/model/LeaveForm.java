package com.klef.jfsd.springboot.model; 
 

import jakarta.persistence.Column; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.Table; 
 
@Entity 
@Table 
public class LeaveForm  
{ 
  @Id 
  @GeneratedValue(strategy =GenerationType.IDENTITY) 
    private int id; 
   
  @Column(nullable = false) 
  private String subject; 
   
  @Column(nullable = false) 
    private String startdate; 
   
  @Column(nullable = false) 
    private String enddate; 
   
  @Column(nullable = false) 
    private String reason; 
//  @Column(nullable = true)
//    private boolean status; 
  public enum TriState {
      TRUE,
      FALSE,
      UNKNOWN
  }

  private TriState status;
   
  @Column(nullable = false) 
    private int empid; 
   
    
 
   
  public int getId() { 
    return id; 
  } 
  public void setId(int id) { 
    this.id = id; 
  } 
  public String getStartdate() { 
    return startdate; 
  } 
  public void setStartdate(String startdate) { 
    this.startdate = startdate; 
  } 
  public String getEnddate() { 
    return enddate; 
  } 
  public void setEnddate(String enddate) { 
    this.enddate = enddate; 
  } 
  public String getReason() { 
    return reason; 
  } 
  public void setReason(String reason) { 
    this.reason = reason; 
  } 
   
  public int getEmpid() { 
    return empid; 
  } 
  public void setEmpid(int empid) { 
    this.empid = empid; 
  } 
   public String getSubject() { 
    return subject; 
  } 
  public void setSubject(String subject) { 
    this.subject = subject; 
  }
public TriState getStatus() {
	return status;
}
public void setStatus(TriState status) {
	this.status = status;
} 
   
}