package com.demo.ecom.dto;

import java.io.Serializable;

public class AuthTokenResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4973639183426115417L;
	
	private String jwtToken;
	
	public AuthTokenResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public AuthTokenResponse() {
		super();
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
