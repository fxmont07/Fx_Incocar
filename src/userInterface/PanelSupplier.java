package userInterface;

import controller.Controller;
import tool.Tool;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSupplier extends JPanel {
    // Attributs
    private Controller controller;
    private JPanel panelButton;
    private JButton modify;
    private JButton delete;
    private SupplierTable supplierTable;
    private ListSelectionModel listSelect;
    private JFrame mainRef;

    // Constructors
    public PanelSupplier(Controller controller, JFrame mainRef) {
        JTable table;
        JScrollPane scrollPane;
        ButtonListener bListener;

        setLayout(new BorderLayout());
        this.controller = controller;
        this.mainRef = mainRef;
        panelButton = new JPanel();

        // JTable
        supplierTable = new SupplierTable(controller);
        table = new JTable(supplierTable);

        Tool.setDisignColor(table);
        scrollPane = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();


        // Buttons
        modify = new JButton("Modifier");
        delete = new JButton("Supprimer");

        // Action
        bListener = new ButtonListener();

        modify.addActionListener(bListener);
        delete.addActionListener(bListener);

        // Add
        add(scrollPane, BorderLayout.CENTER);
        panelButton.add(modify);
        panelButton.add(delete);
        add(panelButton, BorderLayout.SOUTH);

    }


    // Class
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == modify) {
                if (listSelect.getMinSelectionIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Vous devez sélectionner une ligne avant de pouvoir modifier", "Alerte", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    new FormSupplier(controller, supplierTable.getSupplier(listSelect.getMinSelectionIndex()), mainRef);
                }
            } else {
                if (event.getSource() == delete) {
                    if (listSelect.getMinSelectionIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Vous devez sélectionner une ligne avant de pouvoir supprimer", "Alerte", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane jOptionPane = new JOptionPane();
                        int anwser = jOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir supprimer le fournisseur  : " + supplierTable.getSupplier(listSelect.getMinSelectionIndex()).getName() + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (anwser == 0) { // 0 = oui      1 = non
                            try {
                                controller.deleteSupplier(supplierTable.getSupplier(listSelect.getMinSelectionIndex()));
                                JOptionPane.showMessageDialog(null, "Le fournisseur a bien été supprimé", "Suppression", JOptionPane.INFORMATION_MESSAGE);
                                mainRef.getContentPane().removeAll();
                                mainRef.getContentPane().add(new PanelSupplier(controller, mainRef));

                                mainRef.getContentPane().validate();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        }
    }
}
