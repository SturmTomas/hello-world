package model;

import animals.Animal;
import animals.Cat;
import animals.Dog;
import controller.MainController;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Main;

import java.util.HashMap;

public class Receiver implements NotificationListener {

    private Text adverTextDog;
    private Text adverTextCat;
    private AnchorPane adsPane;

    public Receiver(){
        adverTextDog = new Text(800, 30, "");
        adverTextDog.setFill(Paint.valueOf("blue"));
        adverTextCat =  new Text(800, 50, "");
        adverTextCat.setFill(Paint.valueOf("red"));
        MainController mc = MainController.getInstance();
        adsPane = mc.getAdsPane();
        adsPane.getChildren().setAll(adverTextDog,adverTextCat);
    }
    @Override
    public void acceptNotification(Advertisement[] advertisement) {


        HashMap<String, User> hashMap = null;
        try {
            hashMap = SerializeModel.deserialize();
        } catch (HashMapNotFoundException e) {
            e.printStackTrace();
        }
        SimpleUser hMUser = (SimpleUser) hashMap.get(Main.getLoggedUser().getEmail());
        HashMap<String, Animal> animals =   hMUser.getAllAnimals();

        boolean containsDog = false;
        boolean containsCat = false;

        if (animals != null) {
            for (Animal animal : animals.values()) {
                if (animal instanceof Dog) containsDog = true;
                if (animal instanceof Cat) containsCat = true;
            }
        }

        if(containsDog){
            TranslateTransition trans1 = new TranslateTransition();
            trans1.setDuration(Duration.seconds(5));
            adverTextDog.setText(advertisement[0].getAdText());
            adverTextDog.setX(800);
            System.out.println("su accepted dog : "+advertisement[0].getAdText());
            trans1.setToX(-700);
            trans1.setDuration(Duration.seconds(5));
            trans1.setNode(adverTextDog);
            trans1.play();
       }

        if(containsCat){
           TranslateTransition trans2 = new TranslateTransition();
           trans2.setDuration(Duration.seconds(5));
           adverTextCat.setText(advertisement[1].getAdText());
           adverTextCat.setX(800);
           System.out.println("su accepted cat : "+advertisement[1].getAdText());
           trans2.setToX(-400);
           trans2.setDuration(Duration.seconds(5));
           trans2.setNode(adverTextCat);
           trans2.play();
        }

    }
}
