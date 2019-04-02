package controller;
import gui.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

import javafx.event.*;

public class MainController {
	@FXML 
	private Label status;


	@FXML
	AnchorPane rootPane;

	
	
	

	
	public void personData(ActionEvent event) throws Exception {
		
		//nextScene("udaje", "Osobné údaje");
		nextPane("udaje");
	}
	
public void addAnimal(ActionEvent event) throws Exception {
		
		//nextScene("zvierata", "Moje zvieratká");
		nextPane("zvierata");
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
}
