package controller;

import business.Business;
import exception.AlphaNumericException;
import exception.DataAccessException;
import model.*;

import java.util.Date;
import java.util.ArrayList;

public class Controller {
    //Attributs
    private Business business;

    //Constructors
    public Controller() {
        business = new Business();
    }

    //Methods
    // Gestions stockage
    public void closeConnection() {
        business.closeConnection();
    }

    // Obtention
    public String[] getAllLocalities () throws Exception {
        return business.getAllLocalities();
    }
    public Locality getLocality(String name) throws DataAccessException {
        return business.getLocality(name);
    }
    public ArrayList<Supplier> getAllSuppliers() throws Exception {
        return business.getAllSuppliers();
    }
    public Supplier getSupplier(String supplierName) throws DataAccessException {
        return business.getSupplier(supplierName);
    }
    public String[] getAllManufacturers() throws DataAccessException {
        return business.getAllManufacturers();
    }
    public String[] getAllModels(String manufacturer) throws DataAccessException {
        return business.getAllModels(manufacturer);
    }
    public Model getModel(String modelName) throws AlphaNumericException, DataAccessException {
        return business.getModel(modelName);
    }
    public Model getModelWithVehicles(String modelName) throws DataAccessException {
        return business.getModelWithVehicles(modelName);
    }
    public ArrayList<Vehicle> getAllVehicles() throws DataAccessException {
        return business.getAllVehicles();
    }
    public Vehicle getVehicle(String chassisNumber) throws DataAccessException {
        return business.getVehicle(chassisNumber);
    }
    public ArrayList<Vehicle> getAllVehiclesBetweenDates(Date firstDate, Date lastDate) throws DataAccessException {
        return business.getAllVehiclesBetweenDates(firstDate, lastDate);
    }
    public ArrayList<Vehicle> getVehiclesSoldByLocality(String localityName) throws DataAccessException {
        return business.getVehiclesSoldByLocality(localityName);
    }
    public ArrayList<Bill> getAllBills() throws DataAccessException {
        return business.getAllBills();
    }
    public ArrayList<Bill> getAllBillsFromVehicle(String vehicleChassis) throws DataAccessException {
        return business.getAllBillsFromVehicle(vehicleChassis);
    }
    public ArrayList<Bill> getBillsBetween2Dates(java.util.Date firstDate, java.util.Date lastDate, boolean isSaleSelected) throws DataAccessException {
        return business.getBillsBetween2Dates(firstDate, lastDate, isSaleSelected);
    }
    public ArrayList<Client> getAllClients() throws DataAccessException {
        return business.getAllClients();
    }

    // Adds
    public void addSupplier(Supplier supplier) throws DataAccessException{
        business.addSupplier(supplier);
    }
    public void addModel(String manufacturerName, String newModel) throws DataAccessException {
        business.addModel(manufacturerName, newModel);
    }
    public void addVehicle(Vehicle vehicle) throws DataAccessException {
        business.addVehicle(vehicle);
    }
    public void addBill(Bill bill) throws DataAccessException {
        business.addBill(bill);
    }


    // Updates
    public void updateSupplier(Supplier supplier) throws DataAccessException {
        business.updateSupplier(supplier);
    }
    public void updateVehicle(Vehicle vehicle) throws DataAccessException {
        business.updateVehicle(vehicle);
    }

    // Delete
    public void deleteSupplier(Supplier supplier) throws DataAccessException {
        business.deleteSupplier(supplier);
    }
    public void deleteModel(String modelName) throws DataAccessException {
        business.deleteModel(modelName);
    }
    public void deleteVehicle(String vehicleChassis) throws DataAccessException {
        business.deleteVehicle(vehicleChassis);
    }


    //AvailableVehicleCalculator
    public double totalCostOfBuyedCar(ArrayList<Vehicle> vehicles){
        return business.totalCostOfBuyedCar(vehicles);
    }

    public double totalGainOfSoldCar(ArrayList<Vehicle> vehicles){
        return business.totalGainOfSoldCar(vehicles);
    }


    public int numberOfVehicleInSale(ArrayList<Vehicle> vehicles){
        return business.numberOfVehicleInSale(vehicles);
    }

    public double enginePowerToHorsePower(Vehicle vehicle){
        return business.enginePowerToHorsePower(vehicle);
    }

    public double totalProfit(ArrayList<Vehicle> vehicles) {
        return business.totalProfit(vehicles);
    }
}
