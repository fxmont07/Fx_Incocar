package userInterface;

import controller.Controller;
import model.Locality;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormLocality extends JFrame {
    private Controller controller;
    private Container container;

    private JLabel country;
    private JTextField inputContry;

    private JLabel locality;
    private JComboBox localities;

    private JLabel zipCode;
    private JTextField inputZipCode;

    private JLabel newLocality;
    private JTextField inputNewLocality;

    private JPanel buttons;
    private JButton add;
    private JButton delete;
    private JButton cancel;

    public FormLocality(Controller controller) {
        super("Gestion Localités");

        ButtonListener bListener;

        setBounds(600, 200, 290, 250);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });
        setLayout(new FlowLayout());
        setResizable(true);
        container = getContentPane();
        this.controller = controller;

        country = new JLabel("Pays : ");
        inputContry = new JTextField("Belgique");
        inputContry.setColumns(15);

        locality = new JLabel("Localités : ");
        locality.setHorizontalAlignment(SwingConstants.RIGHT);
        try {
            localities = new JComboBox(controller.getAllLocalities());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        newLocality = new JLabel("Nouvelle localité : ");
        inputNewLocality = new JTextField();
        inputNewLocality.setColumns(20);

        zipCode = new JLabel("Code postal : ");
        inputZipCode = new JTextField();
        inputZipCode.setColumns(10);

        buttons = new JPanel();

        // Buttons
        bListener = new ButtonListener();
        add = new JButton("Ajouter");
        add.addActionListener(bListener);
        delete = new JButton("Supprimer");
        delete.addActionListener(bListener);
        cancel = new JButton("Annuler");
        cancel.addActionListener(bListener);

        container.add(country);
        container.add(inputContry);

        container.add(locality);
        container.add(localities);
        container.add(newLocality);
        container.add(inputNewLocality);
        container.add(zipCode);
        container.add(inputZipCode);
        container.add(new JLabel("Suppression possible si non lié"));

        buttons.add(add);
        buttons.add(delete);
        buttons.add(cancel);
        container.add(buttons);

        setVisible(true);
    }

    public String getLocalityNameWithoutZipCode(String localityString) {
        String s = "";
        String [] resSplit = localityString.split(" ");
        if (resSplit.length > 2) {
            for (int i = 0; i < resSplit.length - 1; i++) {
                s += resSplit[i];
            }
        } else {
            return resSplit[0];
        }
        return s;
     }

     public String getZipCode(String localityString) {
         String [] resSplit = localityString.split(" ");

         return resSplit[resSplit.length-1];
     }


    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == add) {
                if (inputNewLocality.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Le champ \"nouvelle localité\" ne doit pas être vide", "Champ vide", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Ajout
                    try {
                        controller.addLocality(new Locality(inputNewLocality.getText().toUpperCase(), inputZipCode.getText(), inputContry.getText()));
                        JOptionPane.showMessageDialog(null, "La localité a bien été ajoutée", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Nom ou code postal déjà présent", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                if (event.getSource() == delete) {
                    if (localities.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Le champ \"localité\" ne doit pas être vide", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            JOptionPane jOptionPane = new JOptionPane();
                            int anwser = jOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir supprimer la localité: " + localities.getSelectedItem() + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (anwser == 0) { // 0 = oui      1 = non
                                try {
                                    controller.deleteLocality(getLocalityNameWithoutZipCode((String)localities.getSelectedItem()), getZipCode((String)localities.getSelectedItem()));
                                    JOptionPane.showMessageDialog(null, "La localité a bien été supprimée : " + localities.getSelectedItem(), "Suppression", JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                dispose();
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
