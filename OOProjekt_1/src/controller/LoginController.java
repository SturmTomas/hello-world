package controller;

import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.*;


public class LoginController   {

	@FXML
	private Button btnreg;
	@FXML
	private TextField pword;
	@FXML
	private TextField reppword;
	@FXML
	private TextField email;
	@FXML
	private AnchorPane rootPane;
	@FXML
	private Label status;	
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Button login;
	
	

	
	@FXML private void initialize() {
		
		btnreg.setOnAction((e)-> {
			try {

				register();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
		username.setText("a@a.a");
		password.setText("a");
	}

	
public void login(ActionEvent event) throws Exception {
		 
			if(LoginModel.login(new SimpleUser(username.getText(),password.getText()))) 
			{
			nextScene("front", "front");
			System.out.println(Main.getLoggedUser());
			return;
			}
	
				status.setText("Prihlásenie neúspešné");
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


@FXML
public void register() throws Exception {
	
	System.out.println("haloo");
		if( !email.getText().matches("[a-zA-Z0-9.-_]+@[a-z]+.[a-z]+")	|| pword.getText().isEmpty() || reppword.getText().isEmpty()) {
		status.setText("Registrácia neúspešná\n");
		return;
		}
		if ( !(reppword.getText().equals(pword.getText()))){
		status.setText("Registrácia neúspešná\n Heslá sa nezhodujú");
		return;
		}
		
		boolean exist = true;
		HashMap<String, SimpleUser> hashMap = null;
		
		try {
			hashMap = SerializeModel.deserialize();
		} catch (HashMapNotFoundException e) {
			exist=false;
		}
		
		if(exist) {
			if(hashMap.get(email.getText()) != null) {
				status.setText("Zadaný email sa už používa, zvolte si iný");
				return;
			};
		}
		
		
		LoginModel.register(new SimpleUser(email.getText(),pword.getText()));
	
			try {
				nextScene("front", "Najlepšia appka na celom šírom svete");
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}


}
		
}


