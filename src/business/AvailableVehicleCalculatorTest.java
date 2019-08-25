package business;

import model.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.*;

public class AvailableVehicleCalculatorTest {
    private AvailableVehicleCalculator availableVehicleCalculator;
    private ArrayList<Vehicle> vehicles;
    private Supplier supplier;
    private Model model;
    private Locality locality;
    private Bill bill;
    private Vehicle vehicle;
    private Client client;


    @org.junit.Before
    public void setUp() throws Exception {
        availableVehicleCalculator = new AvailableVehicleCalculator();
        vehicles = new ArrayList<>();
        locality = new Locality("Bruxelle","6542","Belgique");
        supplier = new Supplier("BE85258","Le fournisseur","Rue du pont","7","0480550022", locality);
        model = new Model("A1",new Manufacturer("AUDI"));
        client = new Client(30, "NomTest", "PrenomTest", "Rue Test", null, null, null, locality );
        vehicle = new Vehicle("TESTERGEZ654ZM111","Bleue",new GregorianCalendar(),1400,"Diesel", 100, 3000.0, new GregorianCalendar(), 80000, "Notes", true, false, false, true, false, false, false, model, null, new ArrayList<>());
        bill = new Bill(false, 3000.0, new GregorianCalendar(), vehicle, client );
        vehicle.addBill(bill);
        vehicles.add(vehicle);

        vehicle = new Vehicle("TESTERGEZ654ZM333","Noire",new GregorianCalendar(),1400,"Diesel", 80, 6000.0, new GregorianCalendar(), 80000, "Notes", false, true, true, true, false, false, false, model, supplier, new ArrayList<>());
        bill = new Bill(true, 7000.0, new GregorianCalendar(), vehicle, client);
        vehicle.addBill(bill);

        vehicles.add(vehicle);
    }

    @org.junit.Test
    public void totalCostOfBuyedCar() {
        Assert.assertEquals(9000.,availableVehicleCalculator.totalCostOfBuyedCar(vehicles),0.1);
    }

    @org.junit.Test
    public void totalGainOfSoldCar() {
        Assert.assertEquals(7000.0, availableVehicleCalculator.totalGainOfSoldCar(vehicles), 0.1);
    }

    @org.junit.Test
    public void numberOfVehicleInSale() {
        Assert.assertTrue(availableVehicleCalculator.numberOfVehicleInSale(vehicles) == 1);
    }

    @org.junit.Test
    public void enginePowerToHorsePower(){
        Assert.assertEquals(135.962,availableVehicleCalculator.enginePowerToHorsePower(vehicles.get(0)),0.1);
        Assert.assertEquals(108.77,availableVehicleCalculator.enginePowerToHorsePower(vehicles.get(1)),0.1);
    }

    @org.junit.Test
    public void totalProfit() {
        Assert.assertEquals(5530.0,availableVehicleCalculator.totalProfit(vehicles),0.1);
    }
}