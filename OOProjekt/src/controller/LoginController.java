package controller;
import database.*;
import gui.*;

import java.io.IOException;
import java.net.URL;
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
import model.LoginModel;
import model.SimpleUser;

public class LoginController implements Initializable  {

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
	
	
	@FXML Label isConnected; 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LoginModel.isDatabaseConnected()) {
		  System.out.println("Connected");
		}
		else {
			isConnected.setText("Not connected");
				System.out.println("Not connected");
		}
	
	}
	
public void login(ActionEvent event) throws Exception {
		
		
		//if(username.getText().equals("") && password.getText().equals("")) 
	
			if(LoginModel.login(new SimpleUser(username.getText(),password.getText()))) {
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

public void register(ActionEvent event) throws Exception {
	
	if( !email.getText().matches("[a-zA-Z0-9.-_]+@[a-z]+.[a-z]+")	|| pword.getText().isEmpty() || reppword.getText().isEmpty()) {
		status.setText("Registrácia neúspešná\n");
		return;
	}
	if ( !(reppword.getText().equals(pword.getText()))){
		status.setText("Registrácia neúspešná\n Heslá sa nezhodujú");
	return;
	}
	
		LoginModel.register(new SimpleUser(email.getText(),pword.getText()));
		nextScene("front", "Najlepšia appka na celom šírom svete");	
}
		
}


