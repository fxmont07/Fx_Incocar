package userInterface;

import controller.Controller;
import model.Client;
import model.Supplier;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class PanelSupplierClient extends JPanel {
    private JRadioButton isSupplier;
    private JRadioButton isClient;
    private ButtonGroup radioGroup;

    private JLabel supplier;
    private JLabel client;

    private JComboBox<String> suppliers;
    private JComboBox<String> clients;

    public PanelSupplierClient(Controller controller , Vehicle vehicle) {
        // Layout
        RadioListener rListener;
        setLayout(new GridLayout(3, 2,10,10));

        isSupplier = new JRadioButton("Fournisseur", true);
        isSupplier.setHorizontalAlignment(SwingConstants.RIGHT);
        isClient = new JRadioButton("Client", false);


        radioGroup = new ButtonGroup();

        //Action Radio
        rListener = new RadioListener();
        isSupplier.addItemListener(rListener);
        isClient.addItemListener(rListener);

        supplier = new JLabel("Fournissseur :");
        supplier.setHorizontalAlignment(SwingConstants.RIGHT);
        client = new JLabel("Client : ");
        client.setHorizontalAlignment(SwingConstants.RIGHT);

        try {
            suppliers = new JComboBox<>();
            for (Supplier supplier : controller.getAllSuppliers()) {
                suppliers.addItem(supplier.getName());
            }
            suppliers.setEnabled(true);

            clients = new JComboBox<>();
            for (Client client : controller.getAllClients()) {
                clients.addItem(client.getLastName() + " " + client.getFirstName());
            }
            clients.setEnabled(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
        }

        if(vehicle != null) { // Observer
            if(vehicle.getSupplier() != null) {
                isSupplier.setSelected(true);
                isClient.setSelected(false);
                suppliers.setSelectedItem(vehicle.getSupplier().getName());

            } else {
                isSupplier.setSelected(false);
                isClient.setSelected(true);
                clients.setSelectedItem(vehicle.getLastBill().getClient().getLastName() + " " + vehicle.getLastBill().getClient().getFirstName());

            }
            suppliers.setEnabled(false);
            clients.setEnabled(false);
            isSupplier.setEnabled(false);
            isClient.setEnabled(false);
        }

        add(isSupplier);
        add(isClient);
        radioGroup.add(isSupplier);
        radioGroup.add(isClient);

        add(supplier);
        add(suppliers);

        add(client);
        add(clients);
    }

    public String getSupplierSelected() {
        if(isSupplier.isSelected()) {
            return (String)suppliers.getSelectedItem();
        }
        return null;
    }

    public String getClientSelected() {
        if(isClient.isSelected()) {
            return (String)clients.getSelectedItem();
        }
        return null;
    }

    public boolean isSupplierSelected() {
        return isSupplier.isSelected();
    }

    public class RadioListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource() == isSupplier && e.getStateChange() == ItemEvent.SELECTED) {
                clients.setEnabled(false);
                suppliers.setEnabled(true);
            } else {
                if (e.getSource() == isClient && e.getStateChange() == ItemEvent.SELECTED) {
                    clients.setEnabled(true);
                    suppliers.setEnabled(false);
                }
            }
        }
    }

}
