package userInterface;

import controller.Controller;
import model.Supplier;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SupplierTable  extends AbstractTableModel {
    private String [] columnNames = {
            "Nom", "TVA", "Adresse", "Tel"
    };

    private ArrayList<Supplier> suppliers;

    public SupplierTable(Controller controller) {
        try {
            suppliers = controller.getAllSuppliers();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return suppliers.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int column) {
        Supplier supplier = suppliers.get(row);
        switch (column) {
            case 0 :
                return supplier.getName();
            case 1 :
                return supplier.getTvaNumber();
            case 2 :
                return supplier.getLocality().getName() + " " + supplier.getLocality().getZipCode() + " - " + supplier.getStreet() + ", " + supplier.getStreetNumber();
            case 3 :
                return supplier.getPhoneNumber();
            default: return  null;
        }
    }

    public Class getColumnClass(int col) {
        return String.class;
    }

    public Supplier getSupplier(int indice) {
        try {
            return suppliers.get(indice);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception, "Erreur table fournisseur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
