package model;

import java.io.*;
import java.util.HashMap;

public class SerializeModel {
   
	
	public static void serialize(HashMap<String, User> hashMap)  {

	try
    {
           FileOutputStream fos =
              new FileOutputStream("hashmap.ser");
           ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(hashMap);
           oos.close();
           fos.close();
           System.out.printf("Success");
    }catch(IOException ioe)
     {
           ioe.printStackTrace();
     }
	}
	public static HashMap<String, User> deserialize() throws HashMapNotFoundException {
		
		HashMap<String, User> hashMap = new HashMap<String, User>();
		  try
	      {
	         FileInputStream fis = new FileInputStream("hashmap.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         hashMap = (HashMap<String,User>) ois.readObject();
	         ois.close();
	         fis.close();
	         return hashMap;
	      
	      }catch(IOException ioe) {
	    	  throw new HashMapNotFoundException();  
	      }
		  catch(ClassNotFoundException c)
	      {
	         System.out.println("Class not found");
	         c.printStackTrace();
	        return null;
	      }

		
	}
}
