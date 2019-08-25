package userInterface;

import controller.Controller;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WindowVehiclesByLocality extends JFrame {
    // Attributs
    private Controller controller;
    private JFrame mainRef;
    private JLabel locality;
    private JComboBox localities;

    private JPanel buttons;
    private JButton valid;
    private JButton cancel;

    // Constructors
    public WindowVehiclesByLocality(Controller controller, JFrame mainRef) {
        super("Recherche des véhicules vendus par localité");
        ButtonListener bListener;
        this.controller = controller;
        this.mainRef = mainRef;
        setLayout(new FlowLayout());
        setBounds(600,300, 400,120);

        locality = new JLabel("Localité : ");
        try {
            localities = new JComboBox(controller.getAllLocalities());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        bListener = new ButtonListener();
        buttons = new JPanel();
        valid = new JButton("Valider");
        valid.addActionListener(bListener);
        cancel = new JButton("Annuler");
        cancel.addActionListener(bListener);

        buttons.add(valid);
        buttons.add(cancel);

        add(locality);
        add(localities);
        add(buttons);

        setVisible(true);
    }

    // Class
    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == valid) {
                ArrayList<Vehicle> vehicles = new ArrayList<>();
                try {
                  vehicles = controller.getVehiclesSoldByLocality((String)localities.getSelectedItem());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                if(vehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Aucun véhicule trouvé pour cette localité");
                } else {
                    mainRef.getContentPane().removeAll();
                    mainRef.getContentPane().add(new PanelVehicle(controller, mainRef, vehicles));
                    mainRef.getContentPane().validate();
                }
            } else {
                if(event.getSource() == cancel) {
                    dispose();
                }
            }
        }
    }
}
