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
	private static HashMap<String, User> hashMap = new HashMap<String, User>();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/gui/gui.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("E-Pets");
		primaryStage.setScene(scene);		
		primaryStage.show();
		
		window = primaryStage;
		
		HashMap<String, User> hashMap = SerializeModel.deserialize();
		
	      System.out.println("Deserialized HashMap");
	      // Display content using Iterator
	      Set set = hashMap.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key: "+ mentry.getKey() + " & Value: ");
	         System.out.println(mentry.getValue());
	      }
	      

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
		public static HashMap<String, User> getHashMap() {
			return hashMap;
		}

		public static void setHashMap(HashMap<String, User> hashMap) {
			Main.hashMap = hashMap;
		}
}
