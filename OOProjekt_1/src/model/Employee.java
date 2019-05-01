package model;

import javafx.scene.control.Button;

public class Employee extends User {

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button payButton) {
		this.deleteButton = payButton;
	}

	private transient Button deleteButton = new Button("Vymazať");
	
	public Employee() {
	super();
	}

	public Employee(String email,String password) {
		super(email, password);
	}

}
