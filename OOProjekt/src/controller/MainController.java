package controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;
import model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.event.*;

public class MainController {

	@FXML private Button rejectOkBtn;
	@FXML private Button acceptOkBtn;
	@FXML private TextArea acceptArea;
	@FXML private TextArea rejectArea;
	@FXML private Label dateLabel;
	@FXML private Label status;
	@FXML AnchorPane rootPane;
	@FXML AnchorPane adsPane;
	private static MainController instance;
	boolean start;

	@FXML private void initialize() {

		instance =this;

		Receiver receiver = new Receiver();
		NotificationSender sender = new NotificationSender();
		sender.addListener(receiver);

		Main.setExecutorService(Executors.newSingleThreadScheduledExecutor());
		ScheduledExecutorService executorService = Main.getExecutorService();
		executorService.scheduleAtFixedRate(sender::sendNotification, 0, 7, TimeUnit.SECONDS);  // () ->  sender.sendNotification()


		HashMap<String,User> hashMap = null;
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			e.printStackTrace();
		}

		SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());
		if(su.getRequestMsg()!=null) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Info");
			alert.setHeaderText(su.getRequestMsg());
			alert.setContentText("Stav účtu si môžete skontrolovať v platbách");
			alert.showAndWait();

			su.setRequestMsg(null);
			SerializeModel.serialize(hashMap);
		}

		if(su.getAcceptedOrders().size()>0){
			acceptArea.setVisible(true);
			acceptOkBtn.setVisible(true);

			for(Order order : su.getAcceptedOrders()){
				acceptArea.appendText(String.join(" ",order.getName(),order.getDate(),"Akceptované","\n" ));
			}

		}else{
			acceptArea.setVisible(false);
			acceptOkBtn.setVisible(false);
		}

		if(su.getRejectedOrders().size()>0){
			rejectArea.setVisible(true);
			rejectOkBtn.setVisible(true);

			for(Order order : su.getRejectedOrders()){
				rejectArea.appendText(String.join(" ",order.getName(),order.getDate(),"Zamietnuté","\n" ));
			}

		}else{
			rejectArea.setVisible(false);
			rejectOkBtn.setVisible(false);
		}


		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d. MMMM, yyyy", new Locale("sk") );
		String formattedNow = now.format(formatter);
		dateLabel.setText("Dnes je "+ formattedNow);

	}
	public static MainController getInstance(){
		return instance;
	}
	public AnchorPane getAdsPane(){
		return adsPane;
	}

	public void myPersonData(ActionEvent event) throws Exception {
		
		nextPane("mojeUdaje");
		//PersonDataController.showMyData(event);
	}

	
	public void personData(ActionEvent event) throws Exception {
		
		nextPane("udaje");
	}
	
	public void addAnimal(ActionEvent event) throws Exception {
		
		nextPane("zvierata");
	}
	public void myAnimals(ActionEvent event) throws Exception {
		
		nextPane("mojeZvierata");
	}
	public void myPayments(ActionEvent event) throws Exception {
		
		nextPane("platby");
	}
	
	public Label getStatus() {
	return status;
}

public void setStatus(Label status) {
	this.status = status;
}

	public void nextScene(String fxmlName, String title) throws IOException {
		Stage window = Main.getPrimaryStage();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/"+fxmlName+".fxml"));
		Scene scene = new Scene(root);
		window.setTitle("E-pets -"+title);
		window.setScene(scene);
	    
		window.getScene().getWindow();
		window.show();
	}
	
	public  void nextPane(String fxmlName) throws Exception {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/"+fxmlName+".fxml"));
		rootPane.getChildren().setAll(pane);
	
	}
	
	public void logout(ActionEvent actionEvent) throws Exception {

		ScheduledExecutorService executorService = Main.getExecutorService() ;
		executorService.shutdown();

		Main.setLoggedUser(null);
		nextScene("gui","E-pets");
	}

	public void toServices(ActionEvent actionEvent) throws Exception {
		nextPane("services");
	}

	public void acceptOk(ActionEvent actionEvent) {
		HashMap<String,User> hashMap = null;
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			e.printStackTrace();
		}

		SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());
		su.setAcceptedOrders(new ArrayList<Order>());
		SerializeModel.serialize(hashMap);

		acceptArea.setVisible(false);
		acceptOkBtn.setVisible(false);
	}

	public void rejectOk(ActionEvent actionEvent) {
		HashMap<String,User> hashMap = null;
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			e.printStackTrace();
		}

		SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());
		su.setRejectedOrders(new ArrayList<Order>());
		SerializeModel.serialize(hashMap);

		rejectArea.setVisible(false);
		rejectOkBtn.setVisible(false);
	}
}
