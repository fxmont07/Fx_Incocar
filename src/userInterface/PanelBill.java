package userInterface;

import controller.Controller;
import model.Bill;
import model.Vehicle;
import tool.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelBill extends JPanel {
    // Attributs
    private Controller controller;

    private BillTable billTable;
    private ListSelectionModel listSelect;

    private JPanel buttons;
    private JButton addPayment;

    private JFrame previousWindow;

    public PanelBill(Controller controller, JFrame previousWindow, ArrayList<Bill> bills) {
        ButtonListener bListener = new ButtonListener();
        JTable table;
        setLayout(new BorderLayout());
        this.controller = controller;
        this.previousWindow = previousWindow;

        // JTable
        if(bills == null) {
            billTable = new BillTable(controller);
        } else {
            billTable = new BillTable(bills);
        }


        table = new JTable(billTable);


        Tool.setDisignColor(table);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();

        buttons = new JPanel();
        addPayment = new JButton("Ajouter Payement");
        addPayment.addActionListener(bListener);
        buttons.add(addPayment);

        add(scrollPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);


    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (listSelect.getMinSelectionIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Vous devez sélectionner une facture");
            } else {
                if (e.getSource() == addPayment) {
                    new WindowPayement(previousWindow, billTable.getBill(listSelect.getMinSelectionIndex()));
                }
            }
        }
    }
}
