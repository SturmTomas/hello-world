package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import animals.Animal;
import javafx.scene.control.Button;

public class SimpleUser extends User implements Serializable {
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8389291704465113457L;
	private String lname;
	private String fname;
	private String adresa;
	private Integer psc;
	private String mesto;
	private String cop;
	private String datnar;
	private HashMap<String, Animal> animals;
	private Double ucet;
	private Double wantMoney;
	private Button acceptMoney;
	private Button rejectMoney;
	private String requestMsg;
	private ArrayList<Order> orders;
	private ArrayList<Order> rejectedOrders = new ArrayList<>();
	private ArrayList<Order> acceptedOrders =  new ArrayList<>();

	public ArrayList<Order> getAcceptedOrders() {
		return acceptedOrders;
	}

	public void setAcceptedOrders(ArrayList<Order> acceptedOrders) {
		this.acceptedOrders = acceptedOrders;
	}

	public ArrayList<Order> getRejectedOrders() {
		return rejectedOrders;
	}

	public void setRejectedOrders(ArrayList<Order> rejectedOrders) {
		this.rejectedOrders = rejectedOrders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	public Button getAcceptMoney() {
		return acceptMoney;
	}

	public void setAcceptMoney(Button acceptMoney) {
		this.acceptMoney = acceptMoney;
	}

	public Button getRejectMoney() {
		return rejectMoney;
	}

	public void setRejectMoney(Button rejectMoney) {
		this.rejectMoney = rejectMoney;
	}

	public Double getWantMoney() {
		return wantMoney;
	}

	public void setWantMoney(Double wantMoney) {
		this.wantMoney = wantMoney;
	}

	public Double getUcet() {
		return ucet;
	}

	public void setUcet(Double ucet) {
		this.ucet = ucet;
	}

	public SimpleUser(String email,String password) {
		super(email, password);
	}	

	public Animal getAnimal(String name) {
		return animals.get(name);
	}
	 public HashMap<String, Animal> getAllAnimals(){
	 
		 return animals;
	 }
	 
	public boolean addAnimal(Animal animal) {
		if(animals == null) {
		 animals = new HashMap<String, Animal>();
		}
		animals.put(animal.getName(),animal);
		return true;
	}

	public boolean addOrder(Order order){
		if(orders == null){
			orders = new ArrayList<Order>();
		}
		orders.add(order);
		return true;
	}

	public boolean removeOrder(Order order){
		return orders.remove(order);
	}

	public SimpleUser(String fname, String lname, String adresa,String datnar
					  ,Integer psc,String cop,String mesto) {
		this.fname = fname;
		this.lname = lname;
		this.adresa = adresa;
		this.datnar = datnar;
		this.psc = psc;
		this.cop = cop;
		this.mesto = mesto;
	}
	
	public String getDatnar() {
		return datnar;
	}
	public void setDatnar(String datnar) {
		this.datnar = datnar;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Integer getPsc() {
		return psc;
	}
	public void setPsc(Integer psc) {
		this.psc = psc;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getCop() {
		return cop;
	}
	public void setCop(String cop) {
		this.cop = cop;
	}

}






