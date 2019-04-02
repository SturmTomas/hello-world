package model;

public class SimpleUser implements User {
    //private String name;
	private String password;
	private String email;
	
	public SimpleUser(String email,String password) {
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public void setPassword(String password) {
		this.password = password;
		
	}

	@Override
	public String getPassword() {
		
		return password;
	}

}
