package userInterface;

import controller.Controller;
import model.Vehicle;
import tool.Tool;

import javax.swing.*;

import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelVehicle extends JPanel {
    // Attributs
    private Controller controller;
    private JPanel panelTable;
    private ListSelectionModel listSelect;
    private VehicleTable vehicleTable;


    private JPanel panelButtons;
    private JButton sell;
    private JButton modify;
    private JButton delete;

    JFrame mainRef;

    // Contructors
    public PanelVehicle(Controller controller, JFrame mainRef, ArrayList<Vehicle> vehicles) {
        ButtonListener bListener;
        JTable table;
        RowSorter<VehicleTable> sorter;
        TableColumn column;
        JScrollPane scrollPane;

        this.controller = controller;
        this.mainRef = mainRef;
        setLayout(new BorderLayout());

        // Pannel
        panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());

        panelButtons = new JPanel();

        // Buttons
        bListener = new ButtonListener();
        sell = new JButton("Vendre");
        sell.addActionListener(bListener);
        modify = new JButton("Modifier");
        modify.addActionListener(bListener);
        delete = new JButton("Supprimer");
        delete.addActionListener(bListener);
        panelButtons.add(sell);
        panelButtons.add(modify);
        panelButtons.add(delete);

        // JTable
        if(vehicles == null) {
            vehicleTable = new VehicleTable(controller);
        } else {
            vehicleTable = new VehicleTable(vehicles);
        }
        table = new JTable(vehicleTable);
        sorter = new TableRowSorter<>(vehicleTable);
        table.setRowSorter(sorter);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(150);


        Tool.setDisignColor(table);
        scrollPane = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();


        panelTable.add(scrollPane, BorderLayout.CENTER);
        add(panelTable, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    public ListSelectionModel getListSelect() {
        return listSelect;
    }

    private void removeVehicle(Vehicle vehicle) {
        try {

            controller.deleteVehicle(vehicle.getChassisNumber());
            JOptionPane.showMessageDialog(null, "Le véhicule à bien été supprimé : " + vehicleTable.getVehicle(listSelect.getMinSelectionIndex()).getModel().getManufacturer().getName() + " " + vehicleTable.getVehicle(listSelect.getMinSelectionIndex()).getModel().getName(), "Supression", JOptionPane.INFORMATION_MESSAGE);
            mainRef.getContentPane().removeAll();
            mainRef.getContentPane().add(new PanelVehicle(controller, mainRef, null));
            mainRef.getContentPane().validate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Vehicle getVehiculeSelected() {
        return vehicleTable.getVehicle(listSelect.getMinSelectionIndex());
    }
    public ArrayList<Vehicle> getAllVehicles() {
        return vehicleTable.getAllVehicles();
    }


    // Class
    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (listSelect.getMinSelectionIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Vous devez sélectionner une ligne avant de pouvoir vendre, modifier ou supprimer", "Alerte", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if(event.getSource() == sell) {
                    new FormSell(controller, vehicleTable.getVehicle(listSelect.getMinSelectionIndex()), mainRef);
                } else {
                    if (event.getSource() == modify) {
                        new FormVehicle(controller, vehicleTable.getVehicle(listSelect.getMinSelectionIndex()), mainRef);
                    } else {
                        if (event.getSource() == delete) {
                            Vehicle vehicle = vehicleTable.getVehicle(listSelect.getMinSelectionIndex());
                            if(vehicle.getLastBill() == null) { // Si pas de facture
                                JOptionPane jOptionPane = new JOptionPane();
                                int anwser = jOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir supprimer ce véhicule ? : " + vehicleTable.getVehicle(listSelect.getMinSelectionIndex()).getModel().getManufacturer().getName() + " " + vehicleTable.getVehicle(listSelect.getMinSelectionIndex()).getModel().getName(), "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (anwser == 0) { // 0 = oui      1 = non
                                    removeVehicle(vehicle);
                                }
                            } else {
                                JOptionPane jOptionPane = new JOptionPane();
                                int anwser = jOptionPane.showConfirmDialog(null,
                                        "Attention ! Le véhicule : " + vehicleTable.getVehicle(listSelect.getMinSelectionIndex()).getModel().getManufacturer().getName() + " " + vehicleTable.getVehicle(listSelect.getMinSelectionIndex()).getModel().getName() +
                                        " est lié à au moins une facture. La suppression effacera les factures et les payements qui en dépendent"
                                        , "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (anwser == 0) { // 0 = oui      1 = non
                                    removeVehicle(vehicle);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
