package tool;

import exception.AlphaNumericException;
import exception.DataAccessException;
import model.*;

import java.util.Date;
import java.util.ArrayList;

public interface DAO {
    // Gestion
    void closeConnection();

    // Obtentions
    String [] getAllLocalities() throws Exception;
    Locality getLocality(String name) throws DataAccessException;
    ArrayList<Supplier> getAllSuppliers() throws Exception;
    Supplier getSupplier(String supplierName) throws DataAccessException;
    Supplier getSupplierTva(String supplierTva) throws DataAccessException;
    String[] getAllManufacturers() throws DataAccessException;
    String[] getAllModels(String manufacturer) throws DataAccessException;
    Model getModel(String modelName) throws AlphaNumericException, DataAccessException;
    Model getModelWithVehicles(String modelName) throws DataAccessException;
    ArrayList<Vehicle> getAllVehicles() throws DataAccessException;
    Vehicle getVehicle(String chassisNumber) throws DataAccessException;
    ArrayList<Vehicle> getAllVehiclesBetweenDates(Date firstDate, Date lastDate) throws DataAccessException;
    ArrayList<Vehicle> getVehiclesSoldByLocality(String localityName) throws DataAccessException;
    ArrayList<Bill> getAllBills() throws DataAccessException;
    ArrayList<Bill> getAllBillsFromVehicle(String vehicleChassis) throws DataAccessException;
    ArrayList<Bill> getBillsBetween2Dates(java.util.Date firstDate, java.util.Date lastDate, boolean isSaleSelected) throws DataAccessException;
    ArrayList<Client> getAllClients()  throws DataAccessException;



    // Adds
    void addSupplier(Supplier supplier) throws DataAccessException;
    void addModel(String manufacturerName, String newModel) throws DataAccessException;
    void addVehicle(Vehicle vehicle) throws DataAccessException;
    void addBill(Bill bill) throws DataAccessException;

    // Update
    void updateSupplier(Supplier supplier) throws DataAccessException;
    public void updateVehicle(Vehicle vehicle) throws DataAccessException;

    // Delete
    void deleteSupplier(Supplier supplier) throws DataAccessException;
    void deleteModel(String modelName) throws DataAccessException;
    void deleteVehicle(String vehicleChassis) throws DataAccessException;
}
