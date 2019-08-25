package model;

import exception.*;
import tool.Tool;

import java.util.ArrayList;

public class Supplier {
    //Attributs
    private String tvaNumber;
    private String name;
    private String street;
    private String streetNumber; //Le numéro peut être 7a par ex
    private String phoneNumber;

    private Locality locality;
    private ArrayList<Vehicle> vehicles;

    //Constructors
    public Supplier(String tvaNumber, String name, String street, String streetNumber, String phoneNumber, Locality locality) throws Exception {
        setTVANumber(tvaNumber);
        setName(name);
        setStreet(street);
        setStreetNumber(streetNumber);
        setPhoneNumber(phoneNumber);
        setLocality(locality);
        vehicles = new ArrayList<>();
    }

    // Getters & Setters
    public String getTvaNumber() {
        return tvaNumber;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Locality getLocality() {
        return locality;
    }


    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setName(String name) throws NotAlphaException {
        if(Tool.isAlphaNumeric(name)) this.name = name;
        else throw new NotAlphaException(name,"nom du fournisseur");
    }

    public void setPhoneNumber(String phoneNumber) throws PhoneNumberException {

        if(Tool.isPhoneNumber(phoneNumber)) this.phoneNumber = phoneNumber;
        else throw new PhoneNumberException(phoneNumber, "téléphone fournisseur");
    }

    public void setTVANumber(String tvaNumber) throws TvaNumberException {
        if(Tool.isNumTVAValid(tvaNumber)) this.tvaNumber = tvaNumber;
        else throw new TvaNumberException(tvaNumber,"numéro de TVA du fournisseur");
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }


    //Methods
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }


}
