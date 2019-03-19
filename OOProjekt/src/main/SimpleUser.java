package main;

public class SimpleUser implements User {
	private String name;
	private String password;
	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public String getName() {
		
		return name;
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
