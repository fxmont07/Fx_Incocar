package userInterface;

import controller.Controller;
import model.Bill;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BillTable extends AbstractTableModel {
    private String[] columnNames = {"N°", "Type", "Date", "Véhicule", "Chassis", "Client", "Montant", "Somme des payements"};
    private ArrayList<Bill> bills;

    public BillTable(Controller controller) {
        try {
            bills = controller.getAllBills();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public BillTable(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return bills.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int column) {
        Bill bill = bills.get(row);
        switch (column) {
            case 0:
                return bill.getId();
            case 1:
                return bill.isSale() ? "Vente" : "Achat";
            case 2:
                return bill.getBillDateInString();
            case 3:
                return bill.getVehicle().getModel().getManufacturer().getName() + " " + bill.getVehicle().getModel().getName();
            case 4:
                return bill.getVehicle().getChassisNumber();
            case 5:
                return bill.getClient().getLastName() + " " + bill.getClient().getFirstName();
            case 6:
                return bill.getBillPrice();
            case 7:
                return bill.sumPayments();
            default:
                return null;
        }
    }

        public Class getColumnClass(int col){
            Class c;
            switch (col) {
                case 0:
                    c = Integer.class;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    c = String.class;
                    break;
                default: c = Double.class;

            }
            return c;
        }

        public Bill getBill(int indice){
            try {
                return bills.get(indice);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception, "Erreur table clients", JOptionPane.ERROR_MESSAGE);
            }
            return null;
        }
}


