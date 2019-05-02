package animals;

import java.io.Serializable;
import java.time.LocalDateTime;

import javafx.scene.control.Button;

public class Cat extends Animal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4285114760066209684L;
	private  final Double payment = 20.0;
	private boolean paid;
	private String paymentDate;
	private transient Button payButton = new Button("Zaplatiť");

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}


	 
	 public Button getPayButton() {
		return payButton;
	}

	public void setPayButton(Button payButton) {
		this.payButton = payButton;
	}

	public Cat(String name, String gender, String animType, String cast) {
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
