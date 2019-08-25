package userInterface;

import controller.Controller;
import model.Bill;
import model.Payment;
import tool.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowPayement extends JFrame {
    private static String[] payementTypes = {"Cash", "Cheque", "Bancontact"};
    private Controller controller;
    private JLabel amountLabel, typeLabel, checkNumberLabel;
    private JComboBox<String> payementTypeComboBox;
    private JTextField amount, checkNumber;
    private Payment payement;
    private Bill bill;

    private JPanel buttons;
    private JButton valid;
    private JButton cancel;
    private JFrame previousWindow;

    public WindowPayement(JFrame previousWindow, Bill bill) {
        super("Fenêtre de payement");
        ButtonListener bListener;
        setBounds(600, 300, 300, 200);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });
        setLayout(new FlowLayout());
        this.bill = bill;
        this.controller = new Controller();
        this.previousWindow = previousWindow;

        amountLabel = new JLabel("Montant : ");
        add(amountLabel);
        amount = new JTextField(this.bill.getBillPrice().toString(), 10);
        amount.setEditable(true);
        amount.addFocusListener(new FocusObserver());
        add(amount);

        typeLabel = new JLabel("Moyen de payement : ");
        add(typeLabel);
        payementTypeComboBox = new JComboBox<>(payementTypes);
        payementTypeComboBox.addItemListener(new CheckListener());
        add(payementTypeComboBox);

        checkNumberLabel = new JLabel("Numéro de chèque : ");
        add(checkNumberLabel);
        checkNumber = new JTextField(15);
        checkNumber.setEditable(false);
        checkNumber.addFocusListener(new FocusObserver());
        add(checkNumber);

        // Action
        bListener = new ButtonListener();
        buttons = new JPanel();

        valid = new JButton("Valider");
        valid.addActionListener(bListener);
        cancel = new JButton("Annuler");
        cancel.addActionListener(bListener);
        buttons.add(valid);
        buttons.add(cancel);

        add(buttons);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == valid) {
                if (amount.getText().equals("") || Double.parseDouble(amount.getText()) <= bill.getBillPrice()) {
                    if ((checkNumber.isEditable() && Tool.isValidCheckNumber(checkNumber.getText())) || !checkNumber.isEditable()) {
                        try {
                            // new Payment
                            //controller.addPayement(payement); //TODO

                            JOptionPane.showMessageDialog(null, "To be continued");
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur ", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Le check doit contenir 10 chiffres");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Montant incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if(event.getSource() == cancel) {
                    dispose();
                }
            }
        }
    }


    private class CheckListener implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            if ("Cheque".compareTo((String) payementTypeComboBox.getSelectedItem()) == 0) {
                checkNumber.setEditable(true);
            } else {
                checkNumber.setEditable(false);
                checkNumber.setBackground(Color.WHITE);
                checkNumber.setText("");
            }
        }
    }

    private class FocusObserver implements FocusListener {
        public void focusGained(FocusEvent event) {
            if (event.getSource() == checkNumber) checkNumber.setBackground(Color.white);
            if (event.getSource() == amount) amount.setBackground(Color.white);
        }

        public void focusLost(FocusEvent event) {
            if (event.getSource() == checkNumber && !Tool.isValidCheckNumber(checkNumber.getText())) {
                checkNumber.setBackground(Tool.RED);
            }

            if (event.getSource() == amount && !Tool.isPositiveDouble(amount.getText())) {
                amount.setBackground(Tool.RED);
            }
        }
    }
}
