package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

	@FXML private void initialize() throws Exception {
		
		ulozitZmeny.setOnAction(e-> {
			try {
				addChanges();
		} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
	}
	
	public void addChanges() throws Exception {

		Integer PSC = null;

		if(psc.getText().equals("")){
			PSC = 0;
		}else {
			try {
				PSC = Integer.valueOf(psc.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Pozor");
				alert.setHeaderText("Nesprávny formát čísla");
				alert.setContentText("Zadajte PSČ ako číslo");
				alert.showAndWait();
				return;
			}
		}

		PersonDataModel.addPersonData( new SimpleUser(fname.getText(),lname.getText(),adresa.getText(),
				datnar.getText(),PSC, cop.getText(), mesto.getText() ));
			

	}
	
}
