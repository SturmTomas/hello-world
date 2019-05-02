package animals;

import java.io.Serializable;

import javafx.scene.control.Button;

public class Dog extends Animal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 51771355202202034L;
	private  final Double payment = 30.0;
	private boolean paid;
	private String paymentDate;

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	private transient Button payButton = new Button("Zaplatiť");
	 
	 public Button getPayButton() {
		return payButton;
	}

	public void setPayButton(Button payButton) {
		this.payButton = payButton;
	}
	
	public Dog(String name, String gender, String animType, String cast) {
		super(name, gender, animType, cast);
		paid = false;
	}


	public Double getPayment() {
		return payment;
	}


	public boolean isPaid() {
		return paid;
	}


	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
