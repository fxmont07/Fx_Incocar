package userInterface;

import controller.Controller;
import exception.DataAccessException;
import model.Vehicle;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class VehicleTable extends AbstractTableModel {
    private String [] columnNames = {"Date", "Marque & Modèle", "Source", "Chassis", "Couleur",
            "1ère Immat", "Prix d'achat", "Prix de vente", "Benefice", "Date vente", "N° Fact", "Km", "Kw",
            "Documents"};
    private ArrayList<Vehicle> vehicles;


    public VehicleTable(Controller controller) {
        try {
            vehicles = controller.getAllVehicles(); //TODO
        } catch (DataAccessException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public VehicleTable(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return vehicles.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int column) {
        Vehicle vehicle = vehicles.get(row);
        switch (column) {
            case 0 :
                return vehicle.getBuyDateInString();
            case 1:
                return vehicle.getModel().getManufacturer().getName() + " " + vehicle.getModel().getName();
            case 2:
                return vehicle.getSupplier() == null ? "Privé" : vehicle.getSupplier().getName(); // vehicle.getBill().getClient() //TODO
            case 3:
                return vehicle.getChassisNumber();
            case 4:
                return vehicle.getColor();
            case 5:
                return vehicle.getFirstRegistrationInString();
            case 6:
                return vehicle.getBuyPrice() == 0 ? null : vehicle.getBuyPrice();
            case 7:
                return vehicle.getLastBill() == null ? null : vehicle.getLastBill().getBillPrice();
            case 8:
                return vehicle.getLastBill() == null ? null : vehicle.getLastBill().getBillPrice() - vehicle.getBuyPrice();
            case 9:
                return vehicle.getLastBill() == null ? null : vehicle.getLastBill().getBillDateInString();
            case 10:
                return vehicle.getLastBill() == null ? null : vehicle.getLastBill().getId();
            case 11 :
                return vehicle.getMileage();
            case 12 :
                return vehicle.getEnginePower();
            case 13 :
                return vehicle.getHasConformityCertificate() && vehicle.getHasImmatCertificate() && vehicle.getHasTechnicalControl() ? "OK" : " / ";
            default: return null;
        }
    }

    public Class getColumnClass(int col) {
        Class c;
        switch (col) {
            case 0 : c = GregorianCalendar.class;
                break;
            case 1 :
            case 2 :
            case 3 :
            case 4 :
                c = String.class;
                break;
            case 5 : c = GregorianCalendar.class;
                break;
            case 6 :
            case 7 :
            case 8 :
                c = Double.class;
                break;
            case 9 : c = String.class;
                break;
            case 10:
            case 11:
            case 12 : c = Integer.class;
                break;
            case 13 : c = String.class;
                break;
            default : c = Boolean.class;
        }
        return c;
    }

    public Vehicle getVehicle(int indice) {
        try{
            return vehicles.get(indice);
        }
        catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception, "Erreur sur la table vehicule", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehicles;
    }
}
