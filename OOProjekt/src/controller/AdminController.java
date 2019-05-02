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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminController {


    @FXML private Label empLabel;
    @FXML private Label ordersLabel;
    @FXML private TableView tableOrders;
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
           tableOrders.setVisible(true);
           ordersLabel.setVisible(true);
           empLabel.setVisible(false);
       }else{
           tableEmp.setVisible(true);
           password.setVisible(true);
           username.setVisible(true);
           registerEmp.setVisible(true);
           tableOrders.setVisible(false);
           ordersLabel.setVisible(false);
           empLabel.setVisible(true);
       }

        updateEmpTableView();
        updateUserTableView();
        updateOrdersTableView();
    }

    private void updateOrdersTableView() throws HashMapNotFoundException {
        HashMap<String, User> hashMap = SerializeModel.deserialize();
        List<Order> listOrders = new ArrayList<Order>();

        for(User user : hashMap.values()) {
            if (user instanceof SimpleUser) {
                List<Order> userOrders =((SimpleUser) user).getOrders();
                for(Order userOrder : userOrders){
                    Button acceptButton = new Button("ANO");
                    acceptButton.setOnMouseClicked(event -> acceptOrder(((SimpleUser) user).getEmail(), userOrder ));
                    userOrder.setAcceptOrder(acceptButton);

                    Button rejectButton = new Button("NIE");
                    rejectButton.setOnMouseClicked(event -> rejectOrder(((SimpleUser) user).getEmail(), userOrder ));
                    userOrder.setRejectOrder(rejectButton);

                    listOrders.add(userOrder);
                }
            }
        }

        ObservableList<Order> availableChoices = FXCollections.observableList(listOrders);

        TableColumn<Order, String> emailColumn = new TableColumn<Order, String>("Email");
        emailColumn.setMinWidth(90);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<SimpleUser, String> serviceColumn = new TableColumn<SimpleUser, String>("Služba");
        serviceColumn.setMinWidth(90);
        serviceColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<SimpleUser, String> dateColumn = new TableColumn<SimpleUser, String>("Čas");
        dateColumn.setMinWidth(60);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<SimpleUser, String> acceptColumn = new TableColumn<SimpleUser, String>();
        acceptColumn.setMaxWidth(70);
        acceptColumn.setCellValueFactory(new PropertyValueFactory<>("acceptOrder"));

        TableColumn<SimpleUser, String> rejectColumn = new TableColumn<SimpleUser, String>();
        rejectColumn.setMaxWidth(70);
        rejectColumn.setCellValueFactory(new PropertyValueFactory<>("rejectOrder"));


        tableOrders.setItems(availableChoices);
        tableOrders.getColumns().setAll(emailColumn,serviceColumn,dateColumn,acceptColumn,rejectColumn);
        tableOrders.setPlaceholder( new Label("Žiadne objednávky"));
    }

    private void acceptOrder( String email, Order order){

        HashMap<String,User> hashMap = null;
        try {
            hashMap = SerializeModel.deserialize();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }

        SimpleUser su = (SimpleUser) hashMap.get(email);

        List<Order> userOrders =su.getOrders();
        for(Order userOrder : userOrders){
            if(userOrder.getName().equals(order.getName()) && userOrder.getDate().equals(order.getDate())){
                su.removeOrder(userOrder);
                break;
            }
        }

        ArrayList<Order> acceptedOrders = su.getAcceptedOrders();
        acceptedOrders.add(order);
        su.setAcceptedOrders(acceptedOrders);

        SerializeModel.serialize(hashMap);

        try {
            updateOrdersTableView();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void rejectOrder( String email, Order order){

        HashMap<String,User> hashMap = null;
        try {
            hashMap = SerializeModel.deserialize();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }

        SimpleUser su = (SimpleUser) hashMap.get(email);

        List<Order> userOrders =su.getOrders();
        for(Order userOrder : userOrders){
            if(userOrder.getName().equals(order.getName()) && userOrder.getDate().equals(order.getDate())){
                su.removeOrder(userOrder);
                break;
            }
        }

        ArrayList<Order> rejectedOrders = su.getRejectedOrders();
        rejectedOrders.add(order);
        su.setRejectedOrders(rejectedOrders);

        SerializeModel.serialize(hashMap);

        try {
            updateOrdersTableView();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }
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
        emailColumn.setMinWidth(90);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<SimpleUser, String> passwordColumn = new TableColumn<SimpleUser, String>("Heslo");
        passwordColumn.setMinWidth(90);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<SimpleUser, String> moneyColumn = new TableColumn<SimpleUser, String>("Žiadosť");
        moneyColumn.setMinWidth(60);
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("wantMoney"));

        TableColumn<SimpleUser, String> acceptColumn = new TableColumn<SimpleUser, String>();
        acceptColumn.setMaxWidth(70);
        acceptColumn.setCellValueFactory(new PropertyValueFactory<>("acceptMoney"));

        TableColumn<SimpleUser, String> rejectColumn = new TableColumn<SimpleUser, String>();
        rejectColumn.setMaxWidth(70);
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
