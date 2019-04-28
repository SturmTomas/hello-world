package model;

import java.util.HashMap;

import controller.*;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import main.Main;


public class MyAnimalModel {
	
	

	
	
	public static void addAnimalToSUser(SimpleUser loggedUser,Animal animal) {
		
		HashMap<String, SimpleUser> hashMap = SerializeModel.deserialize();
		SimpleUser su = hashMap.get(loggedUser.getEmail());
		su.addAnimal(animal);
		hashMap.put(su.getEmail(), su);
		SerializeModel.serialize(hashMap);
		
		hashMap = SerializeModel.deserialize();
		SimpleUser su1 = hashMap.get(loggedUser.getEmail());
		
		System.out.println(" Animal name: "+su1.getAnimal("fido").getName() +"  "+ su1.getAnimal("fido").getCast());
		
	}
}
