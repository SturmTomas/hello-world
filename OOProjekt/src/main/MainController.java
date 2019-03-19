package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.*;

public class MainController {
	@FXML
	private Label status;	
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Button login;
	@FXML
	private Button btnreg;
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField pword;
	@FXML
	private TextField reppword;
	@FXML
	private TextField email;
	

	
	public void login(ActionEvent event) throws Exception {
		
		
		if(username.getText().equals("user") && password.getText().equals("pass")) {
			Stage primaryStage = new Stage();
			status.setText("Prihlasenie uspesne");
			Parent root = FXMLLoader.load(getClass().getResource("front.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("zaver");
			primaryStage.setScene(scene);
		    // get a handle to the stage
		    Stage parentStage = (Stage) login.getScene().getWindow();
		    // do what you have to do
		    parentStage.close();
			primaryStage.show();
			
		}
			else {
				status.setText("Prihlasenie neuspesne");
		}
	}
	
	public void register(ActionEvent event) throws Exception {
		
		if(fname.getText().isEmpty() || lname.getText().isEmpty()|| email.getText().isEmpty()
				|| pword.getText().isEmpty() || reppword.getText().isEmpty()) {
			status.setText("Registrácia neúspešná\n");
		if(reppword.getText().isEmpty() && (reppword.equals(pword))){
			status.setText("Registrácia neúspešná\n Heslá sa nezhodujú");
		}
		}
		else {
			nextStage("front.fxml","front");
			
		}
	}
	public void nextStage(String fxmlName, String title) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
		Scene scene = new Scene(root);
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
	    // get a handle to the stage
	    Stage parentStage = (Stage) login.getScene().getWindow();
	    // do what you have to do
	    parentStage.close();
		primaryStage.show();
	}
}
