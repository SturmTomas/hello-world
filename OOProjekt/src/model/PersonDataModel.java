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
		if(!personData.getAdresa().equals("")) su.setAdresa(personData.getAdresa());
		if(!personData.getCop().equals("")) su.setCop(personData.getCop());
		if(!personData.getFname().equals("")) su.setFname(personData.getFname());
		if(!personData.getLname().equals("")) su.setLname(personData.getLname());
		if(!personData.getDatnar().equals("")) su.setDatnar(personData.getDatnar());
		if(!(personData.getPsc()==0)) su.setPsc(personData.getPsc());
		if(!personData.getMesto().equals("")) su.setMesto(personData.getMesto());
		hm.put(su.getEmail(),su);
		SerializeModel.serialize(hm);
	}

	
}
