package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import animals.Animal;
import animals.Animal.AnimType;
import animals.Animal.Gender;
import animals.Cat;
import animals.Dog;
import animals.Other;
import animals.Reptile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;
import model.*;

import static main.Main.getLoggedUser;


public class AnimalController {
	
	@FXML private TextField name;
	@FXML private ChoiceBox<String>	gender;
	@FXML private ChoiceBox<String> type;
	@FXML private TextField cast;
	
	
	@FXML private void initialize() {
		List<String> list = new ArrayList<String>();		
		for(AnimType val : AnimType.values()) {
			list.add(val.getAnimType());	
		}	
		ObservableList<String> availableChoices = FXCollections.observableList(list);
		type.setItems(availableChoices);
		type.setValue(((ArrayList<String>) list).get(0));
		
		List<String> list1 = new ArrayList<String>();
		for(Gender val1 : Gender.values()) {
			list1.add(val1.getGender());
		}
		ObservableList<String> availableChoices1 = FXCollections.observableList(list1);
		gender.setItems(availableChoices1);
		gender.setValue(((ArrayList<String>) list1).get(0));

	}
	
	public void addAnimal(ActionEvent event) throws Exception {
		
		Animal animal = null;
		String nameStr =  name.getText();
		String genderStr = gender.getValue();
		String typeStr = type.getValue();
		String castStr = cast.getText();
		
		switch( AnimType.get(typeStr)) {
			case  PES : 
				animal = new Dog(nameStr, genderStr, typeStr, castStr);
				break;
			case  MACKA:
				animal = new Cat(nameStr, genderStr, typeStr, castStr);
				break;
			case  PLAZ:
				animal = new Reptile(nameStr, genderStr, typeStr, castStr);
				break;
			case  INE:
				animal = new Other(nameStr, genderStr, typeStr, castStr);
				break;
		}
		
		SimpleUser su = (SimpleUser) getLoggedUser();
		
		MyAnimalModel.addAnimalToSUser(su,animal);

		//Main.mainController.nextPane("platby");
	}

}
