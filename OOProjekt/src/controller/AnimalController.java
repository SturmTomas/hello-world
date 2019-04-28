package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import main.Main;
import model.*;
import model.Animal.AnimType;
import model.Animal.Gender;


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
		
		SimpleUser su = (SimpleUser) Main.getLoggedUser();
		AnimalModel.addAnimalToSUser(su,new Animal(name.getText(),gender.getValue(),type.getValue(),cast.getText()));
		
		   

	}
}
