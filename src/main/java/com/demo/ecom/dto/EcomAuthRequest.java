package com.demo.ecom.dto;

import java.io.Serializable;

public class EcomAuthRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6118509448614830939L;
	
	private String username;
	
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
