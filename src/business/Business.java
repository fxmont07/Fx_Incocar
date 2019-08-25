package business;

import dataAccess.DBIncocar;
import exception.AlphaNumericException;
import exception.DataAccessException;
import model.*;
import tool.DAO;

import java.util.Date;
import java.util.ArrayList;

public class Business {
    // Attributs
    private DAO dao;
    private AvailableVehicleCalculator availableVehicleCalculator;

    // Constructors
    public Business() {
        setDao(new DBIncocar());
        setAvailableVehicleCalculator(new AvailableVehicleCalculator());
    }

    //Getters & Setter
    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public void setAvailableVehicleCalculator(AvailableVehicleCalculator availableVehicleCalculator) {
        this.availableVehicleCalculator = availableVehicleCalculator;
    }

    //Methods
    // Gestion
    public void closeConnection() {
        dao.closeConnection();
    }

    // Obtention
    public String[] getAllLocalities() throws Exception {
        return dao.getAllLocalities();
    }
    public Locality getLocality(String name) throws DataAccessException {
        return dao.getLocality(name);
    }
    public ArrayList<Supplier> getAllSuppliers() throws Exception {
        return dao.getAllSuppliers();
    }
    public Supplier getSupplier(String supplierName) throws DataAccessException {
        return dao.getSupplier(supplierName);
    }
    public String[] getAllManufacturers() throws DataAccessException {
        return dao.getAllManufacturers();
    }
    public String[] getAllModels(String manufacturer) throws DataAccessException {
        return dao.getAllModels(manufacturer);
    }
    public Model getModel(String modelName) throws AlphaNumericException, DataAccessException {
        return dao.getModel(modelName);
    }
    public Model getModelWithVehicles(String modelName) throws DataAccessException {
        return dao.getModelWithVehicles(modelName);
    }
    public ArrayList<Vehicle> getAllVehicles() throws DataAccessException {
        return dao.getAllVehicles();
    }
    public Vehicle getVehicle(String chassisNumber) throws DataAccessException {
        return dao.getVehicle(chassisNumber);
    }
    public ArrayList<Vehicle> getAllVehiclesBetweenDates(Date firstDate, Date lastDate) throws DataAccessException {
        return dao.getAllVehiclesBetweenDates(firstDate, lastDate);
    }
    public ArrayList<Vehicle> getVehiclesSoldByLocality(String localityName) throws DataAccessException {
        return dao.getVehiclesSoldByLocality(localityName);
    }
    public ArrayList<Bill> getAllBills() throws DataAccessException {
        return dao.getAllBills();
    }
    public ArrayList<Bill> getAllBillsFromVehicle(String vehicleChassis) throws DataAccessException {
        return dao.getAllBillsFromVehicle(vehicleChassis);
    }
    public ArrayList<Bill> getBillsBetween2Dates(java.util.Date firstDate, java.util.Date lastDate, boolean isSaleSelected) throws DataAccessException {
        return dao.getBillsBetween2Dates(firstDate, lastDate, isSaleSelected);
    }
    public ArrayList<Client> getAllClients()  throws DataAccessException {
        return dao.getAllClients();
    }

    // Adds
    public void addSupplier(Supplier supplier)throws DataAccessException {
        dao.addSupplier(supplier);
    }
    public void addModel(String manufacturerName, String newModel) throws DataAccessException {
        dao.addModel(manufacturerName, newModel);
    }
    public void addVehicle(Vehicle vehicle) throws DataAccessException {
        dao.addVehicle(vehicle);
    }
    public void addBill(Bill bill) throws DataAccessException {
        dao.addBill(bill);
    }

    // Update
    public void updateSupplier(Supplier supplier) throws DataAccessException {
        dao.updateSupplier(supplier);
    }
    public void updateVehicle(Vehicle vehicle) throws DataAccessException {
        dao.updateVehicle(vehicle);
    }

    // Deletes
    public void deleteSupplier(Supplier supplier) throws DataAccessException {
        dao.deleteSupplier(supplier);
    }
    public void deleteModel(String modelName) throws DataAccessException {
        dao.deleteModel(modelName);
    }
    public void deleteVehicle(String vehicleChassis) throws DataAccessException {
        dao.deleteVehicle(vehicleChassis);
    }


    //AvailableVehicleCalculator
    public double totalCostOfBuyedCar(ArrayList<Vehicle> vehicles){
        return availableVehicleCalculator.totalCostOfBuyedCar(vehicles);
    }
    public double totalGainOfSoldCar(ArrayList<Vehicle> vehicles){
        return availableVehicleCalculator.totalGainOfSoldCar(vehicles);
    }
    public int numberOfVehicleInSale(ArrayList<Vehicle> vehicles){
       return availableVehicleCalculator.numberOfVehicleInSale(vehicles);
    }
    public double enginePowerToHorsePower(Vehicle vehicle){
        return availableVehicleCalculator.enginePowerToHorsePower(vehicle);
    }
    public double totalProfit(ArrayList<Vehicle> vehicles) {
        return availableVehicleCalculator.totalProfit(vehicles);
    }
}

