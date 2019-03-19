package main;

import java.util.ArrayList;

public class UsersArray {
	
	 ArrayList<User> usersArray;

	public UsersArray() {
		//super();
		this.usersArray = new ArrayList<User>();
	}
	
	
	public boolean addNewUser(User user) {
		
		if(usersArray.contains(user)) {
			return false;
		}
		
		return usersArray.add(user);
	}
}
