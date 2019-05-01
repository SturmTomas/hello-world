package model;

import java.io.IOException;
import java.util.HashMap;

import controller.*;
import javafx.scene.control.TextField;
import main.Main;


public class PersonDataModel {


	
	
	public static void addPersonData(SimpleUser personData) throws Exception {
		
		HashMap<String, User> hm = SerializeModel.deserialize();
		SimpleUser su = (SimpleUser) hm.get(Main.getLoggedUser().getEmail());
		su.setAdresa(personData.getAdresa());
		su.setCop(personData.getCop());
		su.setFname(personData.getFname());
		su.setLname(personData.getLname());
		su.setDatnar(personData.getDatnar());
		su.setPsc(personData.getPsc());
		su.setMesto(personData.getMesto());
		
		hm.put(su.getEmail(),su);
		SerializeModel.serialize(hm);
	}

	
}
