package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.Main;
import model.*;



public class PersonDataController {
	
	
	@FXML private  TextField fname;
	@FXML private  TextField lname;
	@FXML private  TextField adresa;
	@FXML private  TextField datnar;
	@FXML private  TextField mesto;
	@FXML private  TextField psc;
	@FXML private  TextField cop;
	@FXML private Button ulozitZmeny;
	@FXML private  TextField fname1;
	
	@FXML private void initialize() throws Exception {
		
		ulozitZmeny.setOnAction(e-> {
			try {
				addChanges();
			System.out.println("daaaaaa");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
	}
	
	public void addChanges() throws Exception {
		
		PersonDataModel.addPersonData( new SimpleUser(fname.getText(),lname.getText(),adresa.getText(),
				datnar.getText(),Integer.parseInt(psc.getText()),mesto.getText(),cop.getText()));
			
		
	}
	
}
