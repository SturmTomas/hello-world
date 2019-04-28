package model;

import java.io.Serializable;

public abstract class User implements Serializable{
	
	
	private String password;
	private String email;
	
	public User(String email,String password) {
		this.email = email;
		this.password = password;
	}
	public User() {
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
