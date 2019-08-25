package model;

import exception.MasqueException;
import exception.PhoneNumberException;
import exception.StreetNumberException;
import exception.TvaNumberException;
import tool.Tool;

import java.util.ArrayList;

public class Client {
    private Integer id;
    private String lastName;
    private String firstName;
    private String street;
    private String streetNumber;
    private String phoneNumber;
    private String tvaNumber;
    private ArrayList<Bill> bills;

    private Locality locality;

    public Client(Integer id, String lastName, String firstName, String street, String streetNumber, String phoneNumber, String tvaNumber, Locality locality)
            throws Exception  {
        setId(id);
        setLastName(lastName);
        setFirstName(firstName);
        setStreet(street);
        setStreetNumber(streetNumber);
        setPhoneNumber(phoneNumber);
        setTvaNumber(tvaNumber);
        setLocality(locality);
    }

    public Client(Integer id, String lastName, String firstName, String street, String streetNumber, String phoneNumber, String tvaNumber, Locality locality, ArrayList<Bill> bills) throws Exception {
        this(id, lastName, firstName, street, streetNumber,phoneNumber, tvaNumber, locality);  {
            setBills(bills);
        }
    }


    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public Locality getLocality(){
        return locality;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
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

    public String getTvaNumber() {
        return tvaNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) throws MasqueException {
        if(!Tool.isNameValid(lastName)) {
            throw new MasqueException(lastName,"Nom de famille");
        }
        this.lastName = lastName;
    }


    public void setFirstName(String firstName) throws MasqueException {
        if(!Tool.isNameValid(lastName)) {
            throw new MasqueException(lastName,"Prénom");
        }
        this.firstName = firstName;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public void setStreet(String street) throws MasqueException {
        if(street != null && !street.isEmpty()){
            if(!Tool.isStreetNameValid(street)) throw new MasqueException(street,"Rue");
            else this.street = street;
        }
        else this.street = null;
    }

    public void setStreetNumber(String streetNumber) throws StreetNumberException {
        if(streetNumber != null && !streetNumber.isEmpty()){
            if(!Tool.isAlphaNumeric(streetNumber)) throw new StreetNumberException(streetNumber,"Numéro de rue");
            else this.streetNumber = streetNumber;
        }
        else this.streetNumber = null;
    }

    public void setPhoneNumber(String phoneNumber) throws PhoneNumberException {
        if(phoneNumber != null && !phoneNumber.isEmpty()){
            if(!Tool.isPhoneNumber(phoneNumber)) throw new PhoneNumberException(phoneNumber,"Numéro de téléphone");
            else this.phoneNumber = phoneNumber;
        }
        else this.phoneNumber = null;
    }

    public void setTvaNumber(String tvaNumber) throws TvaNumberException {
        if(tvaNumber!=null && !tvaNumber.isEmpty()){
            if(!Tool.isNumTVAValid(tvaNumber)) throw new TvaNumberException(tvaNumber,"Numéro de TVA");
            else this.tvaNumber = tvaNumber;
        }
        else this.tvaNumber = null;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public void setClientBills() {
        for(Bill bill : bills) {
            bill.setClient(this);
        }
    }
}
