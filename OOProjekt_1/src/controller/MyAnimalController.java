package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import animals.Animal;
import animals.Animal.AnimType;
import animals.Dog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import model.*;

public class MyAnimalController {

	@FXML TableView<Animal> table;
	
	
	@FXML private void initialize() throws HashMapNotFoundException {
		
		HashMap<String,User> hashMap = SerializeModel.deserialize();
		SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());
		HashMap<String, Animal> animals = su.getAllAnimals();
		List<Animal> list = new ArrayList<Animal>();

		if (animals != null) {
			ArrayList<Animal> valueList = new ArrayList<Animal>(animals.values());
			for (Animal animal : valueList) {
				list.add(animal);
				if (animal instanceof Dog) {
					System.out.println(animal.getAnimType());
				}
			}
		}

		ObservableList<Animal> availableChoices = FXCollections.observableList(list);
		
		TableColumn<Animal, String> nameColumn = new TableColumn<Animal, String>("Meno");
		nameColumn.setMinWidth(125);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Animal, String> genderColumn = new TableColumn<Animal, String>("Pohlavie");
		genderColumn.setMinWidth(125);
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
		
		TableColumn<Animal, String> typeColumn = new TableColumn<Animal, String>("Typ");
		typeColumn.setMinWidth(125);
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("animType"));
		
		TableColumn<Animal, String> castColumn = new TableColumn<Animal, String>("Druh");
		castColumn.setMinWidth(125);
		castColumn.setCellValueFactory(new PropertyValueFactory<>("cast"));

		table.setItems(availableChoices);
		table.getColumns().addAll(nameColumn,genderColumn,typeColumn,castColumn);
		table.setPlaceholder( new Label("Nemáte žiadne registrované zvierata"));
		
	}
}
