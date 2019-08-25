package userInterface;

import controller.Controller;
import model.Client;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ClientTable extends AbstractTableModel {
    private String [] columnNames = {"Nom", "Prénom", "Adresse", "Tel", "TVA"};
    private ArrayList<Client> clients;

    public ClientTable(Controller controller) {
        try {
            clients = controller.getAllClients();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return clients.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int column) {
        Client client = clients.get(row);
        switch (column) {
            case 0 :
                return client.getLastName();
            case 1 :
                return client.getFirstName();
            case 2 :
                return client.getLocality().getName() + " " + client.getLocality().getZipCode() + " - " + client.getStreet() + ", " + client.getStreetNumber();
            case 3 :
                return client.getPhoneNumber();
            case 4 : return client.getTvaNumber();
            default: return  null;
        } //TODO Pour plus tard gestion du pays
    }

    public Class getColumnClass(int col) {
        return String.class;
    }

    public Client getClient(int indice) {
        try {
            return clients.get(indice);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception, "Erreur table clients", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
