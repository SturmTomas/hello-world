package model;



import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;



public class NotificationSender {
    private List<NotificationListener> listeners = new ArrayList<NotificationListener>();
    private short adNum;
    private final short adNumMax;
    private AdsTexts adsTexts;

    private class AdsTexts{
        public final String[] adsDog = {"Lacne krmivo pre psy!","Pridte vyvencit psa do slobody zvierat!","Odlozte si svojho psa do hotela!"};
        public final String[] adsCat = {"Lacne krmivo pre macky!", "Pridte sa pohrat s mackami do slobody zvierat!","Nove obojky pre macky!"};
    }

    public NotificationSender(){
        adsTexts = new AdsTexts();
        adNum = 0;
        adNumMax = (short) adsTexts.adsDog.length;
    }

    public void addListener(NotificationListener toAdd) {
        listeners.add(toAdd);
    }

    public void sendNotification() {

        Advertisement adDog = new AdvertisementDog();
        Advertisement adCat = new AdvertisementCat();

        if(adNum >= adNumMax ) adNum = 0;

        adDog.setAdText(adsTexts.adsDog[adNum]);
        adCat.setAdText(adsTexts.adsCat[adNum]);

        Advertisement[] adDogAndCat = {adDog,adCat};

        System.out.println();
        System.out.println("Sender -> Posielam reklamu: "+adsTexts.adsDog[adNum]);

        for (NotificationListener nl : listeners) {
            try {
                nl.acceptNotification(adDogAndCat);
            } catch (HashMapNotFoundException e) {
                e.printStackTrace();
            }
        }

        adNum++;
    }
}


