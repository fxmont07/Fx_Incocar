package userInterface;

import controller.Controller;
import model.Vehicle;
import thread.ThreadMove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowMain extends JFrame {
    //Attributs
    private Container container;
    private Controller controller;

    private JMenuBar menuBar;

    private JMenu menuVehicle;
    private JMenuItem itemAddVehicle;
    private JMenuItem itemListingVehicles;

    private JMenu menuSupplier;
    private JMenuItem itemAddSupplier;
    private JMenuItem itemListingSuppliers;

    private JMenu menuModel;
    private JMenuItem itemManagementModel;

    private JMenu menuBill;
    private JMenuItem itemListingBills;

    private JMenu menuLocality;
    private JMenuItem itemManagementLocality;

    private JMenu menuResearch;
    private JMenuItem itemVehiclesBought2Dates;
    private JMenuItem itemSalesByLocality;
    private JMenuItem itemBillsBetween2Dates;

    private JMenu menuTool;
    private JMenuItem itemTotalCostBuyedCar;
    private JMenuItem itemTotalGainOfSoldCar;
    private JMenuItem itemNbVehicleInSale;
    private JMenuItem itemEnginePowerToHorsePower;
    private JMenuItem itemTotalProfit;

    private JFrame mainRef;
    private JLabel [] welcomeMessages;


    private PanelVehicle panelVehicle;



    public WindowMain() {
        // Title & Layout
        super("Incocar");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        ItemMenuListener itemMenuListener;
        ThreadMove threadChange;

        setBounds(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight() - 30); // -30 pour éviter la superposition avec la barre de tache au sud

        setLayout(new BorderLayout());
        container = getContentPane();
        controller = new Controller();
        mainRef = this;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                controller.closeConnection();
                System.exit(0);
            }
        });
        setLayout(new BorderLayout());

        // Actions
        itemMenuListener = new ItemMenuListener();

        // MenuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menus
        menuVehicle = new JMenu("Véhicule");
        menuSupplier = new JMenu("Fournisseur");
        menuModel = new JMenu("Modèle");
        menuBill = new JMenu("Facture");
        menuLocality = new JMenu("Localité");
        menuResearch = new JMenu("Recherche");
        menuTool = new JMenu("Outils");


        // Options Vehicle Menu
        itemAddVehicle = new JMenuItem("Ajouter Véhicule");
        itemAddVehicle.addActionListener(itemMenuListener);
        itemListingVehicles = new JMenuItem("Listing Véhicules");
        itemListingVehicles.addActionListener(itemMenuListener);

        // Options Supplier Menu
        itemAddSupplier = new JMenuItem(("Ajouter Fournisseur"));
        itemAddSupplier.addActionListener(itemMenuListener);
        itemListingSuppliers = new JMenuItem("Listing Fournisseurs");
        itemListingSuppliers.addActionListener(itemMenuListener);

        // Options Model Menu
        itemManagementModel = new JMenuItem("Gestion Modèles");
        itemManagementModel.addActionListener(itemMenuListener);

        // Options Bill Menu
        itemListingBills = new JMenuItem("Listing Facture");
        itemListingBills.addActionListener(itemMenuListener);

        // Options Vehicle Menu
        itemManagementLocality = new JMenuItem("Gestion Localités");
        itemManagementLocality.addActionListener(itemMenuListener);

        // Options Recherche
        itemVehiclesBought2Dates = new JMenuItem("Véhicules achetés entre 2 dates");
        itemVehiclesBought2Dates.addActionListener(itemMenuListener);
        itemSalesByLocality = new JMenuItem("Véhicules par localité");
        itemSalesByLocality.addActionListener(itemMenuListener);
        itemBillsBetween2Dates = new JMenuItem("Factures entre 2 dates");
        itemBillsBetween2Dates.addActionListener(itemMenuListener);

        // Options outils
        itemTotalCostBuyedCar = new JMenuItem("Total des coûts d'achats");
        itemTotalCostBuyedCar.addActionListener(itemMenuListener);
        itemTotalGainOfSoldCar = new JMenuItem("Total des ventes");
        itemTotalGainOfSoldCar.addActionListener(itemMenuListener);
        itemTotalProfit = new JMenuItem("Total des ventes TVA déduite");
        itemTotalProfit.addActionListener(itemMenuListener);
        itemNbVehicleInSale = new JMenuItem("Nombre de véhicule en vente");
        itemNbVehicleInSale.addActionListener(itemMenuListener);
        itemEnginePowerToHorsePower = new JMenuItem("Convertisseur KW en Cheval-Vapeur");
        itemEnginePowerToHorsePower.addActionListener(itemMenuListener);

        // Ajout dans la barre de menu
        menuBar.add(menuVehicle);
        menuBar.add(menuSupplier);
        menuBar.add(menuModel);
        menuBar.add(menuLocality);
        menuBar.add(menuBill);
        menuBar.add(menuResearch);
        menuBar.add(menuTool);

        // Ajout dans le menu Vehicule
        menuVehicle.add(itemAddVehicle);
        menuVehicle.add(itemListingVehicles);

        // Ajout dans le menu Fournisseurs
        menuSupplier.add(itemAddSupplier);
        menuSupplier.add(itemListingSuppliers);

        // Ajout dans le menu Model
        menuModel.add(itemManagementModel);

        // Ajout dans le menu Localité
        menuLocality.add(itemManagementLocality);

        // Ajout dans le menu Facture
        menuBill.add(itemListingBills);

        // Ajout dans le menu Recherche
        menuResearch.add(itemVehiclesBought2Dates);
        menuResearch.add(itemSalesByLocality);
        menuResearch.add(itemBillsBetween2Dates);

        // Ajout dans le menu Outils
        menuTool.add(itemTotalCostBuyedCar);
        menuTool.add(itemTotalGainOfSoldCar);
        menuTool.add(itemTotalProfit);
        menuTool.add(itemNbVehicleInSale);
        menuTool.add(itemEnginePowerToHorsePower);

        // Thread
        welcomeMessages = new JLabel[5];
        welcomeMessages[0] = new JLabel("Bienvenue");
        welcomeMessages[1] = new JLabel("chez Incocar");
        welcomeMessages[2] = new JLabel("vendeur de");
        welcomeMessages[3] = new JLabel("véhicules d'occasions");
        welcomeMessages[4] = new JLabel("depuis 1996");
        welcomeMessages[0].setHorizontalAlignment(SwingConstants.CENTER);
        container.add(welcomeMessages[0],BorderLayout.NORTH);

        panelVehicle = new PanelVehicle(controller, mainRef, null);
        container.add(panelVehicle);

        threadChange = new ThreadMove(this);
        threadChange.start();

        setVisible(true);
    }

    //Methods
    public JLabel[] getWelcomeMessages() {
        return welcomeMessages;
    }

    public Container getContainer() {
        return container;
    }

    public class ItemMenuListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == itemAddVehicle) {
                new FormVehicle(controller, null, mainRef);
            } else {
                if(event.getSource() == itemListingVehicles) {
                    container.removeAll();
                    container.add(new PanelVehicle(controller, mainRef, null));
                    container.validate();
                } else {
                    if(event.getSource() == itemAddSupplier) {
                        new FormSupplier(controller, null, mainRef);
                    } else {
                        if(event.getSource() == itemListingSuppliers) {
                            container.removeAll();
                            container.add(new PanelSupplier(controller, mainRef));
                            container.validate();
                        } else {
                            if (event.getSource() == itemManagementModel) {
                                new FormModel(controller);
                            } else {
                                if(event.getSource() == itemListingBills) {
                                    container.removeAll();
                                    container.add(new PanelBill(controller, mainRef, null));
                                    container.validate();
                                } else {
                                    if(event.getSource() == itemManagementLocality) {
                                        new FormLocality(controller);
                                    } else {
                                        if(event.getSource() == itemVehiclesBought2Dates) {
                                            new WindowResearchBetweenDates(mainRef, itemVehiclesBought2Dates.getText());
                                        } else {
                                            if (event.getSource() == itemSalesByLocality) {
                                                new WindowVehiclesByLocality(controller, mainRef);
                                            } else {
                                                if(event.getSource() == itemBillsBetween2Dates) {
                                                    new WindowResearchBetweenDates(mainRef, itemBillsBetween2Dates.getText());
                                                } else {
                                                    if(event.getSource() == itemTotalCostBuyedCar) {
                                                        try {
                                                            JOptionPane.showMessageDialog(null, controller.totalCostOfBuyedCar(panelVehicle.getAllVehicles()) + " €");
                                                        } catch (Exception e) {
                                                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                                        }

                                                    } else {
                                                        if (event.getSource() == itemTotalGainOfSoldCar) {
                                                            try {
                                                                JOptionPane.showMessageDialog(null, controller.totalGainOfSoldCar(panelVehicle.getAllVehicles()) + " €");
                                                            } catch (Exception e) {
                                                                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        } else {
                                                            if (event.getSource() == itemTotalProfit) {
                                                                try {
                                                                    JOptionPane.showMessageDialog(null, controller.totalProfit(panelVehicle.getAllVehicles()) + " € (tva déduite)");
                                                                } catch (Exception e) {
                                                                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            } else {
                                                                if (event.getSource() == itemNbVehicleInSale) {
                                                                    try {
                                                                        JOptionPane.showMessageDialog(null, controller.numberOfVehicleInSale(panelVehicle.getAllVehicles()) + " véhicule(s) en vente"); // Ne se met pas à jour //TODO
                                                                    } catch (Exception e) {
                                                                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                } else {
                                                                    if (event.getSource() == itemEnginePowerToHorsePower) {
                                                                        if (panelVehicle.getListSelect().getMinSelectionIndex() == -1) {
                                                                            JOptionPane.showMessageDialog(null, "Veuillez choisir un véhicule.");
                                                                        } else {
                                                                            Vehicle vehicle = panelVehicle.getVehiculeSelected();
                                                                            double horsePower = controller.enginePowerToHorsePower(vehicle);
                                                                            if (horsePower == 0) {
                                                                                JOptionPane.showMessageDialog(null, "Le véhicule sélectionné n'a pas de puissance enregistrée");
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(null, Math.round(horsePower * 100.0) / 100.0 + " cv", "Convertisseur KW", JOptionPane.INFORMATION_MESSAGE);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}