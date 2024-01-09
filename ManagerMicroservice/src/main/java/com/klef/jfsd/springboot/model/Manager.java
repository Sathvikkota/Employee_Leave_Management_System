package com.klef.jfsd.springboot.model;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="man_table")
public class Manager 
{
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name="man_id")
	   private int id;
	   @Column(name="man_name",nullable=false,length = 50)
	   private String name;
	   @Column(name="man_gender",nullable=false,length = 10)
	   private String gender;
	   @Column(name="man_dob",nullable=false,length = 20)
	   private String dateofbirth;
	   @Column(name="man_department",nullable=false,length = 20)
	   private String department;
	   @Column(name="man_salary",nullable=false)
	   private double salary;
	   @Column(name="man_email",nullable=false,unique = true,length = 30)
	   private String email;
	   @Column(name="man_password",nullable=false,length = 30)
	   private String password;
	   @Column(name="man_location",nullable=false)
	   private String location;
	   @Column(name="man_contact",nullable=false,unique = true)
	   private String contact;
	   @Column(name="man_active",nullable=false)
	   private boolean active; // true or false
	   private Blob profilepic;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", gender=" + gender + ", dateofbirth=" + dateofbirth
				+ ", department=" + department + ", salary=" + salary + ", email=" + email + ", password=" + password
				+ ", location=" + location + ", contact=" + contact + ", active=" + active + "]";
	}
	public Blob getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(Blob profilepic) {
		this.profilepic = profilepic;
	}

}
