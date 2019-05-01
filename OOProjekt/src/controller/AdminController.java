package controller;

import animals.Animal;
import animals.Dog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminController {


    @FXML private Button registerEmp;
    @FXML private TableView tableUser;
    @FXML private TableView tableEmp;
    @FXML private TextField password;
    @FXML private TextField username;


    @FXML private void initialize() throws HashMapNotFoundException {

       // Ked je zamestnanec nalogovany
       if(Main.getLoggedUser() != null){
           tableEmp.setVisible(false);
           password.setVisible(false);
           username.setVisible(false);
           registerEmp.setVisible(false);
       }else{
           tableEmp.setVisible(true);
           password.setVisible(true);
           username.setVisible(true);
           registerEmp.setVisible(true);
       }

        updateEmpTableView();
        updateUserTableView();
    }

    private void updateUserTableView() throws HashMapNotFoundException {
        HashMap<String, User> hashMap = SerializeModel.deserialize();
        List<SimpleUser> listUser = new ArrayList<SimpleUser>();

        for(User user : hashMap.values()){
            if(user instanceof SimpleUser){
                listUser.add((SimpleUser) user);
                if(((SimpleUser) user).getWantMoney() != null){
                   Button acceptButton = new Button("ANO");
                   acceptButton.setOnMouseClicked(event -> acceptWantMoney(((SimpleUser) user).getEmail()));
                   ((SimpleUser) user).setAcceptMoney(acceptButton);

                    Button rejectButton = new Button("NIE");
                    rejectButton.setOnMouseClicked(event -> rejectWantMoney(((SimpleUser) user).getEmail()));
                    ((SimpleUser) user).setRejectMoney(rejectButton);


                }
                //Button deleteButton = new Button("Vymazať");
                //deleteButton.setOnMouseClicked(event -> deleteEmployee(((Employee) user).getEmail()));
                //((Employee) user).setDeleteButton(deleteButton);
            }
        }

        ObservableList<SimpleUser> availableChoices = FXCollections.observableList(listUser);

        TableColumn<SimpleUser, String> emailColumn = new TableColumn<SimpleUser, String>("Email");
        emailColumn.setMinWidth(80);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<SimpleUser, String> passwordColumn = new TableColumn<SimpleUser, String>("Heslo");
        passwordColumn.setMinWidth(80);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<SimpleUser, String> moneyColumn = new TableColumn<SimpleUser, String>("Židosť");
        moneyColumn.setMinWidth(80);
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("wantMoney"));

        TableColumn<SimpleUser, String> acceptColumn = new TableColumn<SimpleUser, String>();
        acceptColumn.setMinWidth(80);
        acceptColumn.setCellValueFactory(new PropertyValueFactory<>("acceptMoney"));

        TableColumn<SimpleUser, String> rejectColumn = new TableColumn<SimpleUser, String>();
        rejectColumn.setMinWidth(80);
        rejectColumn.setCellValueFactory(new PropertyValueFactory<>("rejectMoney"));


        tableUser.setItems(availableChoices);
        tableUser.getColumns().setAll(emailColumn,passwordColumn,moneyColumn,acceptColumn,rejectColumn);
        tableUser.setPlaceholder( new Label("Žiadni registrovaní používatelia"));
    }

    private void acceptWantMoney(String email){
        HashMap<String,User> hashMap = null;
        try {
            hashMap = SerializeModel.deserialize();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }

        SimpleUser su = (SimpleUser) hashMap.get(email);
        su.setUcet(su.getWantMoney() + su.getUcet() );
        su.setWantMoney(null);
        su.setRequestMsg("Žiadosť o navýšenie kreditu bola prijatá administrátorom");
        SerializeModel.serialize(hashMap);
        try {
            updateUserTableView();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void rejectWantMoney(String email){
        HashMap<String,User> hashMap = null;
        try {
            hashMap = SerializeModel.deserialize();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }

        SimpleUser su = (SimpleUser) hashMap.get(email);
        su.setWantMoney(null);
      //  su.setUcet(su.getWantMoney() + su.getUcet() );
        su.setRequestMsg("Žiadosť o navýšenie kreditu bola zamietnutá administrátorom");
        SerializeModel.serialize(hashMap);
        try {
            updateUserTableView();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateEmpTableView() throws HashMapNotFoundException {
        HashMap<String, User> hashMap = SerializeModel.deserialize();
        List<Employee> listEmp = new ArrayList<Employee>();

        for(User user : hashMap.values()){
            if(user instanceof Employee){
                listEmp.add((Employee) user);
                Button deleteButton = new Button("Vymazať");
                deleteButton.setOnMouseClicked(event -> deleteEmployee(((Employee) user).getEmail()));
                ((Employee) user).setDeleteButton(deleteButton);
            }
        }

        ObservableList<Employee> availableChoices = FXCollections.observableList(listEmp);

        TableColumn<Employee, String> emailColumn = new TableColumn<Employee, String>("Email");
        emailColumn.setMinWidth(100);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Employee, String> passwordColumn = new TableColumn<Employee, String>("Heslo");
        passwordColumn.setMinWidth(100);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Employee, String> buttonColumn = new TableColumn<Employee, String>();
        buttonColumn.setMinWidth(100);
        buttonColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        tableEmp.setItems(availableChoices);
        tableEmp.getColumns().setAll(emailColumn,passwordColumn,buttonColumn);
        tableEmp.setPlaceholder( new Label("Žiadni registrovaní zamestnanci"));

    }

    private void deleteEmployee(String email){
        HashMap<String,User> hashMap = null;
        try {
            hashMap = SerializeModel.deserialize();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }
        hashMap.remove(email);

        SerializeModel.serialize(hashMap);
        try {
            updateEmpTableView();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) throws IOException {

        Stage window = Main.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/gui.fxml"));
        Scene scene = new Scene(root);
        window.setTitle("E-pets");
        window.setScene(scene);

        window.getScene().getWindow();
        window.show();

        Main.setLoggedUser(null);
    }


    public void registerEmp(ActionEvent actionEvent) throws HashMapNotFoundException {
        LoginModel.register(new Employee(username.getText(),password.getText()));
        updateEmpTableView();
    }
}
