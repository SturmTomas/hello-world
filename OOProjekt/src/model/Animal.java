package model;
// observer 
import java.io.Serializable;

import javafx.scene.control.ChoiceBox;

public class Animal implements Serializable {
	
	 private String name;
	 private AnimType animtype;
	 private String cast;
	 private Gender gender;
	 public enum AnimType{
		 PES("Pes"),MACKA("Maèka"),PLAZ("Plaz"),INE("Iné");
		 
		 private final String animType;
		 private AnimType(String animType) {
			 
		 this.animType = animType;	
		 }
		 public String getAnimType() {
			 return animType;
		 }

	 }

	 
	 public enum Gender {
		    SAMEC ("Samec"),
		    SAMICA("Samica");

		    private final String gender;
		    Gender(String gender){
		        this.gender = gender;
		    }

		    public String getGender(){
		        return gender;
		    }
	 }
		
	 
	
	
	 public Animal(String name, String gender,String animtype,String cast){
		  this.name =name;
		  this.animtype = AnimType.valueOf(animtype);
		  this.gender = Gender.valueOf(gender);
		  this.cast = cast;
		  	  
	 }
	 public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public void setGender(String gender) {
		
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	} 
	 
}
