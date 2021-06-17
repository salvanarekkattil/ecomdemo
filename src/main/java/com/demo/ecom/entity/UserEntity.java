package com.demo.ecom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="user_details")
public class UserEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
	@Column(name="user_name")
    private String userName;
	
	@Column(name="password")
    private String password;
	
	@Column(name="user_type")
    private String userType;
	
	@Column(name="created_date")
    private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
