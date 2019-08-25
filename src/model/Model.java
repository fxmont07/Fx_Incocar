package model;

import exception.AlphaNumericException;
import tool.Tool;

import java.util.ArrayList;

public class Model {
    private String name;
    private Manufacturer manufacturer;
    private ArrayList<Vehicle> vehicles;

    // Constructors
    public Model (String name, Manufacturer manufacturer) throws AlphaNumericException{
        setName(name);
        this.manufacturer = manufacturer;
        this.vehicles = new ArrayList<>();
    }

    public Model(String name, Manufacturer manufacturer, ArrayList<Vehicle> vehicles)  throws AlphaNumericException{
        setName(name);
        this.manufacturer = manufacturer;
        this.vehicles = vehicles;
    }

    // Getters & Setters
    public String getName(){
        return name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setName(String name) throws AlphaNumericException {
        if(Tool.isAlphaNumeric(name)) this.name = name;
        else throw new AlphaNumericException(name);
    }

    public void setModelInVehucles() {
        for(Vehicle vehicle : vehicles) {
            vehicle.setModel(this);
        }
    }



    // Methods



}
