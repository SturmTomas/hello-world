package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class OrderController {


    @FXML private Button orderBtn;
    @FXML private TextField orderField;

    public void makeOrder(ActionEvent actionEvent) {

        HashMap<String, User> hashMap = null;
        try {
            hashMap = SerializeModel.deserialize();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }

        SimpleUser su = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());

        if(!orderField.getText().equals("")) {
            Order order = new Order();
            order.setName(orderField.getText());
            order.setDate(getTime());
            order.setEmail(su.getEmail());

            su.addOrder(order);
            SerializeModel.serialize(hashMap);
            Stage stage = (Stage) orderBtn.getScene().getWindow();
            stage.close();
        }
    }

    private String getTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedNow = now.format(formatter);
        return formattedNow;
    }
}
