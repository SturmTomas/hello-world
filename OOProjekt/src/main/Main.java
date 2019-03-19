package main;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("E-Pets");
		primaryStage.setScene(scene);		
		primaryStage.show();
		
	}
}
