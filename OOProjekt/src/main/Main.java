package main;
import gui.*;
import database.*;
import controller.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import model.*;
import javafx.scene.*;

public class Main extends Application{
	
	private static Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/gui/gui.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("E-Pets");
		primaryStage.setScene(scene);		
		primaryStage.show();
		
		window = primaryStage;
		
		MainController mainController = new MainController();

	}
	 public static Stage getPrimaryStage() {
	        return window;
	    }

}
