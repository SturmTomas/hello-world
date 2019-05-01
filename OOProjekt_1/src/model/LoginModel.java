package model;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import controller.LoginController;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;


public class LoginModel {


	public static void register(User user) {
		HashMap<String, User> hashMap = null;

		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			hashMap = new HashMap<String, User>();
			SerializeModel.serialize(hashMap);
		}

		if (user instanceof SimpleUser) {
			((SimpleUser) user).setUcet(100.0);
			Main.setLoggedUser(user);
		}
		hashMap.put(user.getEmail(), user);
		SerializeModel.serialize(hashMap);

	}
	
	public static User login(String email, String password ) {
		
		HashMap<String, User> hashMap = null;
		
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			hashMap = new HashMap<String, User>();
			SerializeModel.serialize(hashMap);
		}
		
		User hMUser = hashMap.get(email);
		
		if (hashMap.containsKey(email)) {
			String pw = hMUser.getPassword();

			if (pw.equals(password)) {
				System.out.println(password+"\n"+email);
				Main.setLoggedUser(hMUser);
				return hMUser;
			}		 
		}
		
		return null;
	}



	

}
