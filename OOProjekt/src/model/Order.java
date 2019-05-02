package model;

import javafx.scene.control.Button;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {

    private String name;
    private String date;
    private String email;
    private transient Button acceptOrder;
    private transient Button rejectOrder;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Button getAcceptOrder() {
        return acceptOrder;
    }

    public void setAcceptOrder(Button acceptOrder) {
        this.acceptOrder = acceptOrder;
    }

    public Button getRejectOrder() {
        return rejectOrder;
    }

    public void setRejectOrder(Button rejectOrder) {
        this.rejectOrder = rejectOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
