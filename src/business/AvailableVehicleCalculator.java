package business;

import model.Bill;
import model.Vehicle;

import java.util.ArrayList;

public class AvailableVehicleCalculator {
    private final static double KW_TO_CV_RATE = 1.36; // 1 Cheval-vapeur =  0,735499 kw


    public AvailableVehicleCalculator() {
    }

    public double totalCostOfBuyedCar(ArrayList<Vehicle> vehicles){
        double sum = 0;
        for(Vehicle vehicle : vehicles){
            sum += vehicle.getBuyPrice();
        }
        return sum;
    }

    public double totalGainOfSoldCar(ArrayList<Vehicle> vehicles){
        double sum = 0;
        for(Vehicle vehicle : vehicles){
            for(Bill bill : vehicle.getBills()) {
                if( bill.isSale()) {
                    sum += bill.getBillPrice();
                }
            }
        }
        return sum;
    }


    public int numberOfVehicleInSale(ArrayList<Vehicle> vehicles){
        int nbVehicleInSale = 0;
        for(Vehicle vehicle : vehicles){
            if(vehicle.getOnSale()) nbVehicleInSale++;
        }
        return nbVehicleInSale;
    }

    public double enginePowerToHorsePower(Vehicle vehicle){
        return vehicle.getEnginePower() * KW_TO_CV_RATE;
    }

    public double totalProfit(ArrayList<Vehicle> vehicles) {
        double sum = 0;
        for(Vehicle vehicle : vehicles){
            for(Bill bill : vehicle.getBills()) {
                if (bill.isSale()) {
                    sum += (bill.getBillPrice() * 0.79);
                }
            }
        }
        return sum;
    }
}
