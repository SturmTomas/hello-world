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
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;
import model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.event.*;

public class MainController {
	
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

}
