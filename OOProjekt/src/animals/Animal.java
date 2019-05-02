package animals;
// observer 
import java.io.Serializable;
import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class Animal implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7094783882772274334L;
	private String name;
	 private String animType;
	 private String cast;
	 private String gender;

	public enum AnimType{
		 PES("Pes"),MACKA("Mačka"),PLAZ("Plaz"),INE("Iné");
		 
		 private final String animType;
		 private AnimType(String animType) {
			 
		 this.animType = animType;	
		 }
		 public String getAnimType() {
			 return animType;
		 }
		
		    private static final HashMap<String, AnimType> lookup = new HashMap<String,AnimType>();
		   // staticky blok
		    static
		    {
		        for(AnimType at : AnimType.values())
		        {
		            lookup.put(at.getAnimType(), at);
		        }
		    }
		    public static AnimType get(String animType)
		    {
		        return lookup.get(animType);
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
	 public Animal(String name, String gender,String animType,String cast){
		  this.name =name;
		  this.animType = animType;
		  this.gender = gender;
		  this.cast = cast;  	  
	 }
	  
	 public String getAnimType() {
		return animType;
	}

	public void setAnimType(String animType) {
		this.animType = animType;
	}

	public String getGender() {
		return gender;
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
