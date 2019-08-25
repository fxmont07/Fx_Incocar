package userInterface;

import controller.Controller;
import exception.DataAccessException;
import model.Vehicle;
import tool.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanelModelManufacturer extends JPanel {
    // Attributs
    private Controller controller;

    private JLabel manufacturer;
    private JComboBox manufacturers;

    private JLabel model;
    private JComboBox models;

    // Constructor
    public PanelModelManufacturer(Controller controller, Vehicle vehicle) {
        ComboListener cListener;
        this.controller = controller;

        // Action
        cListener = new ComboListener();

        manufacturer = new JLabel("Marque : ");
        manufacturer.setHorizontalAlignment(SwingConstants.RIGHT);
        model = new JLabel("Modèle *: ");
        model.setHorizontalAlignment((SwingConstants.RIGHT));
        try{
            manufacturers = new JComboBox(controller.getAllManufacturers());
            manufacturers.addItemListener(cListener);
            models = new JComboBox(controller.getAllModels((String)manufacturers.getSelectedItem()));
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        if(vehicle != null) {
            manufacturers.setSelectedItem(vehicle.getModel().getManufacturer().getName());
            models.setSelectedItem(vehicle.getModel().getName());
        }

        add(manufacturer);
        add(manufacturers);
        add(model);
        add(models);

        setLayout(new GridLayout(2,2 ,5 ,5));
    }

    // Methods
    public String getModelSelected() {
        return (String)models.getSelectedItem();
    }

    public String getManufacturerSelected() {
        return  (String)manufacturers.getSelectedItem();
    }

    public void lockPanel() {
        manufacturers.setEnabled(false);
        models.setEnabled(false);
    }


    // Class
    public class ComboListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            models.removeAllItems();
            try {
                for (String string : controller.getAllModels((String)manufacturers.getSelectedItem())){
                    models.addItem(string);
                }
            } catch (DataAccessException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
