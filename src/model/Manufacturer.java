package model;

import exception.NotAlphaException;
import tool.Tool;

import java.util.ArrayList;

public class Manufacturer {
    //Attributs
    private String name;
    private ArrayList<Model> models;

    //Constructor
    public Manufacturer(String name) throws NotAlphaException{
        this.setName(name);
        this.models = new ArrayList<Model>();
    }

    // Getters & Setters
    public String getName(){
        return name;
    }

    public void setName(String name) throws NotAlphaException{
        if(Tool.isAlpha(name)) this.name = name;
        else throw new NotAlphaException(name,"Nom de marque automobile");
    }

    //Methods
    public void addModel(Model model) {

    }


}
