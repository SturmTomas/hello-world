package users;

import java.util.ArrayList;

public class UsersArray {  // serializable 
	
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