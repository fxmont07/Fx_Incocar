package model;

import exception.NotAlphaException;
import exception.ZipCodeException;
import tool.Tool;

import java.util.ArrayList;

public class Locality {
    //Attributs
    private String name;
    private String zipCode;
    private String country;
    private ArrayList<Client> clients;
    private ArrayList<Supplier> suppliers;

    //Constructors
    public Locality (String name, String zipCode, String country) throws Exception { // Utiliser pour addSupplier pas necessaire de renvoyer les listes
        this(name, zipCode,country, new ArrayList<>(), new ArrayList<>());
    }

    public Locality(String name, String zipCode, String country, ArrayList<Client> clients, ArrayList<Supplier> suppliers) throws Exception {
        setName(name);
        setZipCode(zipCode);
        setCountry(country);
        setClients(clients);
        setSuppliers(suppliers);
    }


    //Getters & Setters
    public String getName(){
        return name;
    }

    public String getZipCode(){
        return zipCode;
    }

    public  String getCountry(){
        return country;
    }

    public void setName(String name) throws NotAlphaException{
        if(Tool.isAlpha(name)) this.name = name;
        else throw new NotAlphaException(name,"libellé de la localité");
    }

    public void setZipCode(String zipCode) throws ZipCodeException{
        if(Tool.isZipCode(zipCode)) this.zipCode = zipCode;
        else throw new ZipCodeException(zipCode,"code postal");
    }

    public void setCountry(String country) throws NotAlphaException{
        if(Tool.isAlpha(country)) this.country = country;
        else throw new NotAlphaException(country,"pays");
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void setSuppliers(ArrayList<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    //Methods
    public void addSupplier(Supplier supplier){
        if(!suppliers.contains(supplier)) suppliers.add(supplier);
    }

    public void addClient(Client client){
        if(!clients.contains(client)) clients.add(client);
    }



}