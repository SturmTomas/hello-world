package model;

public interface NotificationListener {
    void acceptNotification(Advertisement[] advertisement) throws HashMapNotFoundException;
}
