package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.Main;
import model.HashMapNotFoundException;
import model.SerializeModel;
import model.SimpleUser;
import model.User;

import java.io.IOException;
import java.util.HashMap;

public class ServicesController {

    @FXML private TextField mestoField;
    @FXML private TextField serviceField;
    @FXML private WebView webView;

    private WebEngine webEngine;

    @FXML
    private void initialize() {


        webEngine = webView.getEngine();

        webView.setOnMouseClicked(event ->{
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/gui/order.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Chcete sa objednať?");
            stage.setScene(scene);
            stage.show();

        });

        HashMap<String, User> hashMap = null;
        try {
            hashMap = SerializeModel.deserialize();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }

        SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());

        if(su.getMesto()!=null && !su.getMesto().equals("")){
            loadMap(su.getMesto(),"psi salon veterinari");
        }else{
            loadMap("Bratislava","psi salon veterinari");
        }


    }

    void loadMap(String city, String service){
        if( city.equals("")) city = "Bratislava";
        if( service.equals("")) service = "psi salon veterinari";

        webEngine.load("https://www.google.com/maps/search/"+city+"+"+service.trim().replace(" ","+"));
    }

    public void searchMap(ActionEvent actionEvent) {
        loadMap(mestoField.getText(),serviceField.getText());
    }
}
