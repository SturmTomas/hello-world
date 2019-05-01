package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import animals.Animal;
import animals.Cat;
import animals.Dog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import model.HashMapNotFoundException;
import model.SerializeModel;
import model.SimpleUser;

public class PaymentController {

	@FXML private Label nedostatokLabel;
	@FXML private Label ucetLabel;
	@FXML private TableView<Animal> tableToPay;
	@FXML private TableView<Animal> tablePaid;
	
	
	@FXML private void initialize() throws HashMapNotFoundException {
		updateTableView();
	}

	private void updateTableView() throws HashMapNotFoundException {
		HashMap<String,SimpleUser> hashMap = SerializeModel.deserialize();
		SimpleUser su = hashMap.get(Main.getLoggedUser().getEmail());
		ucetLabel.setText("Zostatok na úète: "+ su.getUcet().toString()+ " EUR");

		HashMap<String, Animal> animals = su.getAllAnimals();
		List<Animal> listToPay = new ArrayList<Animal>();
		List<Animal> listPaid = new ArrayList<Animal>();

		if (animals != null ) {
			ArrayList<Animal> valueList = new ArrayList<Animal>(animals.values());
			for (Animal animal : valueList) {
				if (!(animal instanceof Dog) && !(animal instanceof Cat)) {
					listToPay.add(animal);
				} else {
					if (animal instanceof Dog) {
						if (!((Dog) animal).isPaid()) {
							listToPay.add(animal);
						} else {
							listPaid.add(animal);
						}
						Button payButton = new Button("Zaplati");
						payButton.setOnMouseClicked(event -> payForAnimal(animal.getName()));
						((Dog) animal).setPayButton(payButton);
					}
					if (animal instanceof Cat) {
						if (!((Cat) animal).isPaid()) {
							listToPay.add(animal);
						} else {
							listPaid.add(animal);
						}
						Button payButton = new Button("Zaplati");
						payButton.setOnMouseClicked(event -> payForAnimal(animal.getName()));
						((Cat) animal).setPayButton(payButton);

					}
				}
			}
		}

		// Table animals to pay
		ObservableList<Animal> availableChoicesToPay = FXCollections.observableList(listToPay);

		TableColumn<Animal, String> nameColumn = new TableColumn<Animal, String>("Meno");
		nameColumn.setMinWidth(125);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Animal, String> typeColumn = new TableColumn<Animal, String>("Typ");
		typeColumn.setMinWidth(100);
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("animType"));

		TableColumn<Animal, String> costColumn = new TableColumn<Animal, String>("Poplatok");
		costColumn.setMinWidth(75);
		costColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));

		TableColumn<Animal, String> buttonColumn = new TableColumn<Animal, String>();
		buttonColumn.setMinWidth(100);
		buttonColumn.setCellValueFactory(new PropertyValueFactory<>("payButton"));

		tableToPay.setItems(availableChoicesToPay);
		tableToPay.getColumns().setAll(nameColumn,typeColumn,costColumn,buttonColumn);
		tableToPay.setPlaceholder( new Label("Nemáte žiadne registrované alebo nezaplatené zvierata"));


		// Table animals paid
		ObservableList<Animal> availableChoicesPaid = FXCollections.observableList(listPaid);

		TableColumn<Animal, String> nameColumnPaid = new TableColumn<Animal, String>("Meno");
		nameColumnPaid.setMinWidth(100);
		nameColumnPaid.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Animal, String> typeColumnPaid = new TableColumn<Animal, String>("Typ");
		typeColumnPaid.setMinWidth(100);
		typeColumnPaid.setCellValueFactory(new PropertyValueFactory<>("animType"));

		TableColumn<Animal, String> typeColumnDate = new TableColumn<Animal, String>("Dátum platby");
		typeColumnDate.setMinWidth(160);
		typeColumnDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

		tablePaid.setItems(availableChoicesPaid);
		tablePaid.getColumns().setAll(nameColumnPaid,typeColumnPaid,typeColumnDate);
		tablePaid.setPlaceholder( new Label("Nemáte žiadne zaplatené zvierata"));
	}

	private void payForAnimal(String animalName)  {
		HashMap<String,SimpleUser> hashMap = null;
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			e.printStackTrace();
		}
		SimpleUser su = hashMap.get(Main.getLoggedUser().getEmail());
		HashMap<String, Animal> animals = su.getAllAnimals();
		Animal animal = animals.get(animalName);
		if( animal instanceof Dog){
			if(su.getUcet() >= ((Dog)animal).getPayment()){
				su.setUcet(su.getUcet()-((Dog)animal).getPayment());
				nedostatokLabel.setText("");
				((Dog)animal).setPaid(true);
				((Dog)animal).setPaymentDate(getTime());
			}else{
				nedostatokLabel.setText("Nedostatok peòazí na úète!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Transakcia neprebehla!");
				alert.setContentText("Nedostatok peòazí na úète!");
				alert.showAndWait();
			}
		}
		if( animal instanceof Cat){
			if(su.getUcet() >= ((Cat)animal).getPayment()) {
				su.setUcet(su.getUcet() - ((Cat) animal).getPayment());
				nedostatokLabel.setText("");
				((Cat) animal).setPaid(true);
				((Cat) animal).setPaymentDate(getTime());
			}else{
				nedostatokLabel.setText("Nedostatok peòazí na úète!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Transakcia neprebehla!");
				alert.setContentText("Nedostatok peòazí na úète!");
				alert.showAndWait();
			}
		}

		SerializeModel.serialize(hashMap);
		try {
			updateTableView();
		} catch (HashMapNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String getTime(){
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedNow = now.format(formatter);
		return formattedNow;
	}
	
	
}
