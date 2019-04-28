package main;
import gui.*;
import database.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import controller.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import model.*;
import javafx.scene.*;

public class Main extends Application{
	
	private static User loggedUser;
	private static Stage window;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//HashMap<String, SimpleUser> hashMap = new HashMap<String, SimpleUser>();
		//SerializeModel.serialize(hashMap);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/gui.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("E-Pets");
		primaryStage.setScene(scene);		
		primaryStage.show();
		
		window = primaryStage;
	
		      

	}
	 public static Stage getPrimaryStage() {
	        return window;
	    }
		public static User getLoggedUser() {
			return loggedUser;
		}
		public static void setLoggedUser(User loggedUser) {
			Main.loggedUser = loggedUser;
		}

}
