package model;

import java.io.IOException;
import java.util.HashMap;

import animals.Animal;
import controller.*;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import main.Main;


public class MyAnimalModel {
	
	

	
	
	public static void addAnimalToSUser(SimpleUser loggedUser,Animal animal) throws Exception {
		
		HashMap<String, User> hashMap = SerializeModel.deserialize();
		SimpleUser su = (SimpleUser) hashMap.get(loggedUser.getEmail());
		su.addAnimal(animal);
		hashMap.put(su.getEmail(), su);
		SerializeModel.serialize(hashMap);
		

		
	}
}
