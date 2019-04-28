package model;
import database.*;

import java.sql.*;
import java.util.HashMap;

import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;


public class LoginModel {
	
	
	public static void register(SimpleUser simpleUser) {
		
		HashMap<String, SimpleUser> hashMap = SerializeModel.deserialize();
		
		hashMap.put(simpleUser.getEmail(), simpleUser);
		SerializeModel.serialize(hashMap);
		Main.setLoggedUser(simpleUser);

	}
	
	public static boolean login(SimpleUser simpleUser) {
		HashMap<String, SimpleUser> hashMap = SerializeModel.deserialize();
		SimpleUser hMUser = hashMap.get(simpleUser.getEmail());
		if (hashMap.containsKey(simpleUser.getEmail())) {
			
			String pw = hMUser.getPassword();
			String sp = simpleUser.getPassword();
			if (pw.equals(sp)) {
				
				System.out.println(simpleUser.getPassword()+"\n"+simpleUser.getEmail());
				
				Main.setLoggedUser(hMUser);
				
				return true;}
			
	//	}else {
	//			return false;
	//		}
		 
		}
		return false;
	}
	

}
