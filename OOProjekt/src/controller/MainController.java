package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;
import model.PersonDataModel;

import java.io.IOException;

import javafx.event.*;

public class MainController {
	
	@FXML private Label status;
	@FXML AnchorPane rootPane;
	
	
	
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


		   Main.setLoggedUser(null);
		   nextScene("gui","E-pets");
		}



}
