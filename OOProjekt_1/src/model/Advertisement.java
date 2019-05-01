package model;

public abstract class Advertisement {

    protected String adText = "This is advertisement text";

    public void setAdText(String text){
        adText = text;
    }

    abstract public String getAdText();

}
