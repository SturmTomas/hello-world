package model;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

import controller.LoginController;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;


public class LoginModel {
	
	
	public static void register(SimpleUser simpleUser)  {
		HashMap<String, SimpleUser> hashMap = null;
	
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			hashMap = new HashMap<String, SimpleUser>();
			SerializeModel.serialize(hashMap);
		}

		simpleUser.setUcet(100.0);
		hashMap.put(simpleUser.getEmail(), simpleUser);
		SerializeModel.serialize(hashMap);
		Main.setLoggedUser(simpleUser);
	
			

	}
	
	public static boolean login(SimpleUser simpleUser) {
		
		HashMap<String, SimpleUser> hashMap = null;
		
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			hashMap = new HashMap<String, SimpleUser>();
			SerializeModel.serialize(hashMap);
		}
		
		SimpleUser hMUser = hashMap.get(simpleUser.getEmail());
		
		if (hashMap.containsKey(simpleUser.getEmail())) {
			String pw = hMUser.getPassword();
			String sp = simpleUser.getPassword();
			
			if (pw.equals(sp)) {
				System.out.println(simpleUser.getPassword()+"\n"+simpleUser.getEmail());
				Main.setLoggedUser(hMUser);
				return true;
			}		 
		}
		
		return false;
	}
	

}
