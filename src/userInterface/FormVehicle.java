package userInterface;

import controller.Controller;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;


public class FormVehicle extends JFrame {
    // Attributs
    private Controller controller;
    private Container container;
    private Vehicle vehicle;

    private PanelSupplierClient panelSupplierClient;
    private PanelModelManufacturer panelModelManufacturer;
    private PanelVehicleFeatures panelVehicleFeatures;
    private PanelDoc panelDoc;
    private PanelPub panelPub;

    private JPanel buttons;
    private JButton refresh;
    private JButton valid;
    private JButton cancel;

    private JFrame mine;
    private JFrame mainRef;

    public FormVehicle(Controller controller, Vehicle vehicle, JFrame mainRef) {
        super("Ajouter Véhicule");
        ButtonListener bListener;

        setBounds(600,100, 600,650);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });
        container = getContentPane();
        this.mainRef = mainRef;
        this.controller = controller;
        mine = this;


        if(vehicle != null) {
            this.vehicle = vehicle;
            setBounds(600,100, 600,680);
            setTitle("Modifier Véhicule");
        }

        panelSupplierClient = new PanelSupplierClient(controller , vehicle);
        panelModelManufacturer = new PanelModelManufacturer(controller, vehicle);
        panelVehicleFeatures = new PanelVehicleFeatures(vehicle);

        panelDoc = new PanelDoc(vehicle);
        panelPub = new PanelPub(vehicle);

        buttons = new JPanel();

        // Buttons
        bListener = new ButtonListener();
        refresh = new JButton("Actualiser");
        refresh.addActionListener(bListener);

        valid = new JButton(vehicle == null ?"Ajouter": "Modifier");
        valid.addActionListener(bListener);

        cancel = new JButton("Annuler");
        cancel.addActionListener(bListener);

        buttons.add(refresh);
        buttons.add(valid);
        buttons.add(cancel);

        container.add(panelSupplierClient);
        container.add(panelModelManufacturer);
        container.add(panelVehicleFeatures);
        container.add(new JLabel("Les champs qui disposent d'une * sont obligatoires"));
        container.add(panelDoc);
        container.add(panelPub);
        panelPub.setVisible(vehicle == null);

        container.add(buttons);
        setLayout(new FlowLayout());
        setVisible(true);
    }


    public void reload() {
        new FormVehicle(controller, vehicle, mainRef); // removeAll sur le container et validate garde les valeurs des inputs précédents
        mine.dispose();
    }

    public void refreshTable() {
        mainRef.getContentPane().removeAll();
        mainRef.getContentPane().add(new PanelVehicle(controller, mainRef, null));
        mainRef.getContentPane().validate();
    }

    public Vehicle createVehicleSupplier() {
        try {
            return new Vehicle(panelVehicleFeatures.getChassisNumber(), panelVehicleFeatures.getColor(), panelVehicleFeatures.getFirstDateRegistion(),
                    panelVehicleFeatures.getEngineCylinder(), panelVehicleFeatures.getEngineEnergy(), panelVehicleFeatures.getEnginePower(),
                    panelVehicleFeatures.getBuyPrice(), new GregorianCalendar(), panelVehicleFeatures.getMileage(),
                    panelVehicleFeatures.getNotes(), panelVehicleFeatures.getIsOnSale(), panelDoc.getHasImmatCertificate(), panelDoc.getHasTechnicalControl(), panelDoc.getHasConformityCertificate(),
                    controller.getModel(panelModelManufacturer.getModelSelected()), controller.getSupplier(panelSupplierClient.getSupplierSelected()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public Vehicle createVehicleModified() { // constructeur sans date d'achat pour les modifs
        try {
            return new Vehicle(panelVehicleFeatures.getChassisNumber(), panelVehicleFeatures.getColor(), panelVehicleFeatures.getFirstDateRegistion(),
                    panelVehicleFeatures.getEngineCylinder(), panelVehicleFeatures.getEngineEnergy(), panelVehicleFeatures.getEnginePower(),
                    panelVehicleFeatures.getBuyPrice(), panelVehicleFeatures.getMileage(),
                    panelVehicleFeatures.getNotes(), panelVehicleFeatures.getIsOnSale(), panelDoc.getHasImmatCertificate(), panelDoc.getHasTechnicalControl(), panelDoc.getHasConformityCertificate(),
                    panelPub.getIsSellingOnAutoscout(), panelPub.getIsSellingOnFacebook(), panelPub.getIsSellingOnSecondHand(),
                    controller.getModel(panelModelManufacturer.getModelSelected()), controller.getSupplier(panelSupplierClient.getSupplierSelected()),
                    controller.getAllBillsFromVehicle(panelVehicleFeatures.getChassisNumber()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == refresh) {
                reload();
            } else {
                if (event.getSource() == valid) {
                    if (panelVehicleFeatures.isFormValid() && panelModelManufacturer.getModelSelected() != null) {
                        try {
                            if (vehicle == null) { // Ajouter
                                if (controller.getVehicle(panelVehicleFeatures.getChassisNumber()) == null) { // Si pas déjà en BD
                                    if (panelSupplierClient.isSupplierSelected()) {
                                        try {
                                            controller.addVehicle(createVehicleSupplier());
                                            JOptionPane.showMessageDialog(null, "Le véhicule a bien été ajouté");
                                            dispose();
                                            refreshTable();
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        // Ajout client => facture //TODO
                                    }
                                } else {  // Dans le cas où il veut ajouter mais que le chassis existe déjà
                                    JOptionPane jOptionPane = new JOptionPane();
                                    int anwser = jOptionPane.showConfirmDialog(null, "Ce véhicule existe déjà. Voulez-vous mettre à jour les informations encodées ? \n" + "          Modèle : " + panelModelManufacturer.getModelSelected() + "\n          Chassis : " + panelVehicleFeatures.getChassisNumber(), "Confirmation", JOptionPane.YES_NO_OPTION);
                                    if (anwser == 0) { // 0 = oui      1 = non
                                        try {
                                            controller.updateVehicle(createVehicleModified());
                                            JOptionPane.showMessageDialog(null, "Modifications enregistrées", "Information", JOptionPane.INFORMATION_MESSAGE);
                                            dispose();
                                            refreshTable();
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                            } else { // Modifier
                                try {
                                    controller.updateVehicle(createVehicleModified());
                                    JOptionPane.showMessageDialog(null, "Modifications enregistrées", "Information", JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                    refreshTable();
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } else { // Formulaire mal remplit
                        JOptionPane.showMessageDialog(null, "Certains champs sont  incorrectes", "Alerte", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (event.getSource() == cancel) {
                    dispose();
                }
            }
        }
    }
}
