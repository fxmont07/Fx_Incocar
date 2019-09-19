package userInterface;

import controller.Controller;
import model.Bill;
import model.Vehicle;
import tool.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FormSell extends JFrame {
    // Attributs
    private Container container;
    private Controller controller;

    private Vehicle vehicle;
    private PanelDisplaySell panelDisplaySell;

    private JLabel sellPrice;
    private JTextField inputSellPrice;

    private JPanel buttons;
    private JButton confirm;
    private JButton cancel;

    private JFrame mainRef;


    // Contructors
    public FormSell(Controller controller, Vehicle vehicle, JFrame mainRef) {
        super("Vendre Véhicule");
        ButtonListener bListener;

        setBounds(300,100, 1050,600);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });
        container = getContentPane();
        this.controller = controller;
        this.vehicle = vehicle;
        this.mainRef = mainRef;
        panelDisplaySell = new PanelDisplaySell(controller, vehicle, this);

        sellPrice = new JLabel("Prix de vente* : ");
        inputSellPrice = new JTextField();
        inputSellPrice.setColumns(20);


        // Action & Buttons
        bListener = new ButtonListener();
        buttons = new JPanel();
        confirm = new JButton("Confirmer");
        confirm.addActionListener(bListener);
        cancel = new JButton("Annuler");
        cancel.addActionListener(bListener);

        buttons.add(confirm);
        buttons.add(cancel);


        container.add(panelDisplaySell);
        container.add(sellPrice);
        container.add(inputSellPrice);
        container.add(buttons);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == confirm) {
                if(inputSellPrice.getText().isEmpty() || !Tool.isPositiveDouble(inputSellPrice.getText())) {
                    inputSellPrice.setBackground(Tool.RED);
                    JOptionPane.showMessageDialog(null, "Prix de vente manquant ou incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    if(!panelDisplaySell.hasClientSelected()) {
                        JOptionPane.showMessageDialog(null, "Vous devez sélectionner un client");
                    } else {
                        Bill bill = new Bill(true, Double.parseDouble(inputSellPrice.getText()), vehicle, panelDisplaySell.getClientSelected());
                        try {
                            controller.addBill(bill);
                            JOptionPane.showMessageDialog(null, "Vente effectuée", "Succès", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            mainRef.getContentPane().removeAll();
                            mainRef.getContentPane().add(new PanelVehicle(controller, mainRef, null));
                            mainRef.getContentPane().validate();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                if(event.getSource() == cancel) {
                    dispose();
                }
            }
        }
    }
}
