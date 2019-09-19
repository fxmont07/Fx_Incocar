package userInterface;

import controller.Controller;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormModel extends JFrame {
    // Attributs
    private Controller controller;
    private Container container;

    private PanelModelManufacturer panelModelManufacturer;

    private JLabel newModel;
    private JTextField inputNewModel;

    private JButton add;
    private JButton delete;
    private JButton cancel;

    // Contructors
    public FormModel(Controller controller) {
        // Title
        super("Gestion Modèle");

        ButtonListener bListener;

        setBounds(600,200, 300,200);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });
        setLayout(new FlowLayout());
        setResizable(false);
        container = getContentPane();
        this.controller = controller;
        panelModelManufacturer = new PanelModelManufacturer(controller, null);

        newModel = new JLabel("Nouveau Modèle : ");
        newModel.setHorizontalAlignment(SwingConstants.RIGHT);
        inputNewModel = new JTextField();
        inputNewModel.setColumns(15);

        // Buttons
        bListener = new ButtonListener();
        add = new JButton("Ajouter");
        add.addActionListener(bListener);
        delete = new JButton("Supprimer");
        delete.addActionListener(bListener);
        cancel = new JButton("Annuler");
        cancel.addActionListener(bListener);

        container.add(panelModelManufacturer);
        container.add(newModel);
        container.add(inputNewModel);
        container.add(add);
        container.add(delete);
        container.add(cancel);

        setVisible(true);
    }


    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == add) {
                if (inputNewModel.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Le champ \"nouveau modèle\" ne doit pas être vide", "Champ vide", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        controller.addModel(panelModelManufacturer.getManufacturerSelected(), inputNewModel.getText().toUpperCase());
                        JOptionPane.showMessageDialog(null, "Le modèle a bien été ajouté à " + panelModelManufacturer.getManufacturerSelected(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                if (event.getSource() == delete) {
                    if (panelModelManufacturer.getModelSelected() == null) {
                        JOptionPane.showMessageDialog(null, "Le champ \"modèle\" ne doit pas être vide", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            Model model = controller.getModelWithVehicles(panelModelManufacturer.getModelSelected());
                            if (model.getVehicles().size() == 0) {
                                JOptionPane jOptionPane = new JOptionPane();
                                int anwser = jOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir supprimer le modèle : " + panelModelManufacturer.getModelSelected() + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (anwser == 0) { // 0 = oui      1 = non
                                    try {
                                        controller.deleteModel(panelModelManufacturer.getModelSelected());
                                        JOptionPane.showMessageDialog(null, "Le modèle a bien été supprimé : " + panelModelManufacturer.getModelSelected(), "Suppression", JOptionPane.INFORMATION_MESSAGE);
                                        dispose();
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else {
                                // TODO Voir doc pour ON CASCADE SET NULL
                                // TODO Futur : possible de update un modèle si faute d'orthogrpahe
                                JOptionPane.showMessageDialog(null, "Impossible de supprimer ce modèle car des véhicules sont liés à cette marque");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                } else {
                    if (event.getSource() == cancel) {
                        dispose();
                    }
                }
            }
        }
    }
}


