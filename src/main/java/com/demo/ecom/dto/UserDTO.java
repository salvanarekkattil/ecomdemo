package com.demo.ecom.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8633799005346867136L;
	
	private UserDTO(UserBuilder builder) {
		this.id = builder.id;
		this.userName = builder.userName;
		this.userType = builder.userType;
		this.createdDate = builder.createdDate;
	}

    private Long id;
    
    private String userName;
	
    private String userType;
	
    private Date createdDate;

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserType() {
		return userType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public static class UserBuilder {
		
		private Long id;
	    
	    private String userName;
		
	    private String userType;
		
	    private Date createdDate;
 
        public UserBuilder() {
        }
        
        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public UserBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }
        
        public UserBuilder userType(String userType) {
            this.userType = userType;
            return this;
        }
        
        public UserBuilder createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }
        
        public UserDTO build() {
        	UserDTO userDTO =  new UserDTO(this);
            return userDTO;
        }
    }
	
}
