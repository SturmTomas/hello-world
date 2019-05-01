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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import model.HashMapNotFoundException;
import model.SerializeModel;
import model.SimpleUser;
import model.User;

public class PaymentController {

	@FXML private TextField moneyField;
	@FXML private Label nedostatokLabel;
	@FXML private Label ucetLabel;
	@FXML private TableView<Animal> tableToPay;
	@FXML private TableView<Animal> tablePaid;
	
	
	@FXML private void initialize() throws HashMapNotFoundException {
		updateTableView();
	}

	private void updateTableView() throws HashMapNotFoundException {
		HashMap<String, User> hashMap = SerializeModel.deserialize();
		SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());
		ucetLabel.setText("Zostatok na ˙Ëte: "+ su.getUcet().toString()+ " EUR");

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
						Button payButton = new Button("Zaplatiù");
						payButton.setOnMouseClicked(event -> payForAnimal(animal.getName()));
						((Dog) animal).setPayButton(payButton);
					}
					if (animal instanceof Cat) {
						if (!((Cat) animal).isPaid()) {
							listToPay.add(animal);
						} else {
							listPaid.add(animal);
						}
						Button payButton = new Button("Zaplatiù");
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
		tableToPay.setPlaceholder( new Label("Nem·te ûiadne registrovanÈ alebo nezaplatenÈ zvierata"));


		// Table animals paid
		ObservableList<Animal> availableChoicesPaid = FXCollections.observableList(listPaid);

		TableColumn<Animal, String> nameColumnPaid = new TableColumn<Animal, String>("Meno");
		nameColumnPaid.setMinWidth(100);
		nameColumnPaid.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Animal, String> typeColumnPaid = new TableColumn<Animal, String>("Typ");
		typeColumnPaid.setMinWidth(100);
		typeColumnPaid.setCellValueFactory(new PropertyValueFactory<>("animType"));

		TableColumn<Animal, String> typeColumnDate = new TableColumn<Animal, String>("D·tum platby");
		typeColumnDate.setMinWidth(160);
		typeColumnDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

		tablePaid.setItems(availableChoicesPaid);
		tablePaid.getColumns().setAll(nameColumnPaid,typeColumnPaid,typeColumnDate);
		tablePaid.setPlaceholder( new Label("Nem·te ûiadne zaplatenÈ zvierata"));
	}

	private void payForAnimal(String animalName)  {
		HashMap<String,User> hashMap = null;
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			e.printStackTrace();
		}
		SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());
		HashMap<String, Animal> animals = su.getAllAnimals();
		Animal animal = animals.get(animalName);
		if( animal instanceof Dog){
			if(su.getUcet() >= ((Dog)animal).getPayment()){
				su.setUcet(su.getUcet()-((Dog)animal).getPayment());
				nedostatokLabel.setText("");
				((Dog)animal).setPaid(true);
				((Dog)animal).setPaymentDate(getTime());
			}else{
				nedostatokLabel.setText("Nedostatok peÚazÌ na ˙Ëte!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Transakcia neprebehla!");
				alert.setContentText("Nedostatok peÚazÌ na ˙Ëte!");
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
				nedostatokLabel.setText("Nedostatok peÚazÌ na ˙Ëte!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Transakcia neprebehla!");
				alert.setContentText("Nedostatok peÚazÌ na ˙Ëte!");
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


	public void requestMoney(ActionEvent actionEvent) {

		HashMap<String,User> hashMap = null;
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			e.printStackTrace();
		}
		SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());
		Double suma = null;
		try {
			suma = Double.valueOf(moneyField.getText());
		}catch(NumberFormatException e){
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Pozor");
			alert.setHeaderText("Nespr·vny form·t sumy");
			alert.setContentText("Zadajte sumu ako ËÌslo");
			alert.showAndWait();
			return;
		}

		if(su.getWantMoney() == null) {
			su.setWantMoney(suma);
		    SerializeModel.serialize(hashMap);
			moneyField.setText("");

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Info");
			alert.setHeaderText("éiadosù bola zaslan·!");
			alert.setContentText("PoËkajte na schv·lenie ûiadosti administr·torom");
			alert.showAndWait();
		}else{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Pozor");
			alert.setHeaderText("Predch·dzaj˙ca éiadosù sa eöte vybavuje!");
			alert.setContentText("PoËkajte na vybavenie ûiadosti administr·torom");
			alert.showAndWait();
		}
	}
}
