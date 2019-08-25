package userInterface;

import controller.Controller;
import model.Payment;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PayementTable extends AbstractTableModel {
    private String [] columnNames = {"Type", "Montant"};
    private ArrayList<Payment> payements;

    public PayementTable(Controller controller) {
        try {
            //payements = controller.getAllPayementsFormVehicule ...
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    // TODO A faire d'abord les factures ajouter des factures à un véhicule ensuite les listers ensuite les payements
    // Dans le formaulaire de modif véhicle mettre les factures
    // Table Facture

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return payements.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int column) {
        Payment payement = payements.get(row);
        switch (column) {
            case 0 :
                return payement.getType();
            case 1 :
                return payement.getAmount();
            case 2 :
            default: return  null;
        }
    }

    public Class getColumnClass(int col) {
        Class c;
        switch (col) {
            case 0 : c = String.class;
                break;
            default: c = Double.class;
                break;
        }
        return c;
    }

    public Payment getPayement(int indice) {
        try {
            return payements.get(indice);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception, "Erreur table payement", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
