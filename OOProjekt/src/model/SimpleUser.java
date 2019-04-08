package model;

import java.io.Serializable;

public class SimpleUser extends User implements Serializable {
    
	private String password;
	private String email;
	private String lname;
	private String fname;
	private String adresa;
	private Integer psc;
	private String mesto;
	private String cop;
	private String datnar;
	
	private Animal animal;
	
	
	public SimpleUser(String email,String password) {
		super(email, password);
	}	
	public SimpleUser(Animal animal) {
		animal = this.animal;
	}
	
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public SimpleUser(String fname, String lname, String adresa,String datnar
					  ,Integer psc,String cop,String mesto,Animal animal) {
		this.fname = fname;
		this.lname = lname;
		this.adresa = adresa;
		this.datnar = datnar;
		this.psc = psc;
		this.cop = cop;
		this.mesto = mesto;
		this.animal = animal;
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






