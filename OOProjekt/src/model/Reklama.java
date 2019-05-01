package model;



import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

interface NotificationListener {
    void acceptNotification(String msg);
}

class Sender {
    private List<NotificationListener> listeners = new ArrayList<NotificationListener>();

    private String[] advertisments = {"Lacne krmivo pre psy!","Lacne krmivo pre macky!","Odlozte si svoje zviera do hotela!", "Pridte vyvencit psa do slobody zvierat!"};


    public void addListener(NotificationListener toAdd) {
        listeners.add(toAdd);
    }

    public void sendNotification() {

        int randAd = ThreadLocalRandom.current().nextInt(0, advertisments.length);

        System.out.println();
        System.out.println("Sender -> Posielam reklamu: "+advertisments[randAd]);

        for (NotificationListener hl : listeners)
            hl.acceptNotification(advertisments[randAd]);
    }
}

class Recipient implements NotificationListener {

    String name;

    public Recipient(String name){
        this.name = name;
    }

    @Override
    public void acceptNotification(String msg) {
        System.out.println(name+" -> Prijal som reklamu: "+msg);
    }
}

public class Reklama {

    public static void main(String[] args) {
        Sender sender = new Sender();
        Recipient recipient1 = new Recipient("User1");
        Recipient recipient2 = new Recipient("User2");
        sender.addListener(recipient1);
        sender.addListener(recipient2);

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate( sender::sendNotification, 0, 3, TimeUnit.SECONDS);  // () ->  sender.sendNotification()
    }

}

