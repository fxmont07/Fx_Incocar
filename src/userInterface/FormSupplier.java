package userInterface;

import controller.Controller;
import model.Locality;
import model.Supplier;
import tool.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormSupplier extends JFrame {
    //Attributs
    private Controller controller;
    private Container container;

    private JPanel panelInformations;

    private Supplier supplier;

    private JLabel name;
    private JTextField inputName;

    private JLabel tva;
    private JTextField inputTva;

    private JLabel locality;
    private JComboBox localities;

    private JLabel street;
    private JTextField inputStreet;

    private JLabel number;
    private JTextField inputNumber;

    private  JLabel phone;
    private JTextField inputPhone;

    private JButton valid;
    private JButton cancel;

    private JFrame mainRef;

    public FormSupplier(Controller controller, Supplier supplier, JFrame mainRef) { // Si null ajoute d'un fournisseur, si pas modif un fournisseur
        // Title & Layout
        super("Ajouter un fournisseur");
        ImageIcon icon;
        InputListener iListener;

        setBounds(600,200, 450,300);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });
        setLayout(new FlowLayout());
        container = getContentPane();
        icon = new ImageIcon(".\\.\\img\\settings.png");
        panelInformations = new JPanel();
        panelInformations.setLayout(new GridLayout(6,2,5,5));
        if(supplier != null) {
            this.supplier = supplier;
        }
        this.controller = controller;
        this.mainRef = mainRef;

        // Action
        iListener = new InputListener();

        name = new JLabel("Nom* : ");
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        name.setIcon(icon);
        name.setToolTipText("Le nom doit contenir des lettres et/ou des chiffres");
        inputName = new JTextField();
        inputName.addFocusListener(iListener);

        tva = new JLabel("TVA* : ");
        tva.setHorizontalAlignment(SwingConstants.RIGHT);
        tva.setIcon(icon);
        tva.setToolTipText("Le numéro de TVA doit commencer par 2 à 3 lettres en majuscules et des chiffres");
        inputTva = new JTextField();
        inputTva.addFocusListener(iListener);

        locality = new JLabel("Localité : ");
        locality.setHorizontalAlignment(SwingConstants.RIGHT);
        try {
            localities = new JComboBox(controller.getAllLocalities());
            localities.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        street = new JLabel("Rue : ");
        street.setHorizontalAlignment(SwingConstants.RIGHT);
        inputStreet = new JTextField();
        inputStreet.addFocusListener(iListener);

        number = new JLabel("N° : ");
        number.setHorizontalAlignment(SwingConstants.RIGHT);
        inputNumber = new JTextField();


        phone = new JLabel("Tel : ");
        phone.setHorizontalAlignment(SwingConstants.RIGHT);
        phone.setIcon(icon);
        phone.setToolTipText("Le numéro de téléphone peut contenir les carractères ([0-9] , + , , /");
        inputPhone = new JTextField();

        if(supplier != null) {
            inputName.setText(supplier.getName());
            inputTva.setText(supplier.getTvaNumber());
            localities.setSelectedItem(supplier.getLocality().getName());
            inputStreet.setText(supplier.getStreet());
            inputNumber.setText(supplier.getStreetNumber());
            inputPhone.setText(supplier.getPhoneNumber());
        }

        valid = new JButton("Valider");
        cancel = new JButton("Annuler");

        if(supplier == null) {
            AddButtonListener addListener = new AddButtonListener();
            valid.addActionListener(addListener);
            cancel.addActionListener(addListener);
        } else {
            ModifyButtonListener modifyListener = new ModifyButtonListener();
            valid.addActionListener(modifyListener);
            valid.setName("Modifier");
            inputTva.setEditable(false);
            cancel.addActionListener(modifyListener);
        }




        // Display
        panelInformations.add((name));
        panelInformations.add(inputName);

        panelInformations.add(tva);
        panelInformations.add(inputTva);

        panelInformations.add(locality);
        panelInformations.add(localities);

        panelInformations.add(street);
        panelInformations.add(inputStreet);

        panelInformations.add(number);
        panelInformations.add(inputNumber);

        panelInformations.add(phone);
        panelInformations.add(inputPhone);
        panelInformations.setSize(200,200);

        container.add(panelInformations);
        container.add(valid);
        container.add(cancel);

        setVisible(true);
    }

    public boolean isFromValid() {
        return Tool.isAlphaNumeric(inputName.getText()) &&
                Tool.isNumTVAValid(inputTva.getText()) &&
                Tool.isPhoneNumber(inputPhone.getText());
    }

    public class InputListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == inputName) {
                inputName.setBackground(  Tool.isAlphaNumeric(inputName.getText()) && !(inputName.getText().equals("")) ? Color.WHITE  : Tool.RED  );
            }
            if(e.getSource() == inputTva) {
                inputTva.setBackground(  Tool.isNumTVAValid(inputTva.getText()) && !(inputTva.getText().equals("")) ? Color.WHITE  : Tool.RED  );
            }
            if(e.getSource() == inputPhone) {
                inputTva.setBackground(  Tool.isNumTVAValid(inputTva.getText()) ? Color.WHITE  : Tool.RED  );
            }
        }
    }

    public class AddButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == valid) {
                if(isFromValid()) {
                    try {
                        Locality locality = controller.getLocality((String)localities.getSelectedItem());

                        controller.addSupplier(
                                new Supplier(
                                inputTva.getText(), inputName.getText(), inputStreet.getText(), inputNumber.getText(),
                                inputPhone.getText(), locality
                        ));
                        JOptionPane.showMessageDialog(null, "Le fournisseur a bien été ajouté", "Succès",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        mainRef.getContentPane().removeAll();
                        mainRef.getContentPane().add(new PanelSupplier(controller, mainRef));
                        mainRef.getContentPane().validate();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Les champs \"nom\" et \"TVA\" ne sont pas remplits correctement","Champs Incorrects", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(event.getSource() == cancel) {
                dispose();
            }
        }
    }

    public class ModifyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == valid) {
                if (isFromValid()) {
                    try {
                        Locality locality = controller.getLocality((String) localities.getSelectedItem());
                        controller.updateSupplier(
                                new Supplier(inputTva.getText(), inputName.getText(), inputStreet.getText(),
                                        inputNumber.getText(), inputPhone.getText(), locality)
                        );
                        JOptionPane.showMessageDialog(null, "Le fournisseur a bien été modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        mainRef.getContentPane().removeAll();
                        mainRef.getContentPane().add(new PanelSupplier(controller, mainRef));
                        mainRef.getContentPane().validate();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
