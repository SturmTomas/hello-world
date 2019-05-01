package controller;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Main;
import model.HashMapNotFoundException;
import model.SerializeModel;
import model.SimpleUser;
import model.User;

public class MyPersonDataController {

	@FXML private  TextField fname1;
	@FXML private  TextField lname1;
	@FXML private  TextField adresa1;
	@FXML private  TextField datnar1;
	@FXML private  TextField mesto1;
	@FXML private  TextField psc1;
	@FXML private  TextField cop1;
	
	@FXML private void initialize() throws HashMapNotFoundException {

		HashMap<String, User> hashMap = SerializeModel.deserialize();
		SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());

		if(su != null) {
			fname1.setText(su.getFname());
			lname1.setText(su.getLname());
			adresa1.setText(su.getAdresa());
			datnar1.setText(su.getDatnar());
			mesto1.setText(su.getMesto());
			psc1.setText(su.getPsc() == null ? "" : su.getPsc().toString());
			cop1.setText(su.getCop());
			fname1.setEditable(false);
			lname1.setEditable(false);
			adresa1.setEditable(false);
			datnar1.setEditable(false);
			mesto1.setEditable(false);
			psc1.setEditable(false);
			cop1.setEditable(false);
		}
	}

}
