package userInterface;

import controller.Controller;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelClient extends JPanel {
    // Attributs
    private Controller controller;
    private ClientTable clientTable;
    private ListSelectionModel listSelect;
    private JFrame previousWindow;
    private JPanel buttons;
    private JButton add;

    // Constructors
    public PanelClient(Controller controller, JFrame previousWindow) {
        setLayout(new BorderLayout());
        JTable table;
        JScrollPane scrollPane;
        ButtonListener bListener;

        this.controller = controller;
        this.previousWindow = previousWindow;

        // JTable
        clientTable = new ClientTable(controller);
        table = new JTable(clientTable);

        scrollPane = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();

        // Action & buttons
        bListener = new ButtonListener();
        buttons = new JPanel();
        add = new JButton("Nouveau Client");
        add.addActionListener(bListener);
        buttons.add(add);

        // Add
        add(scrollPane,BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

    }

    public Client getClient() {
        return clientTable.getClient(listSelect.getMinSelectionIndex());
    }
    public boolean hasClientSelected() {
        return listSelect.getMinSelectionIndex() >= 0;
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == add) {
                // TODO new FromClient
            }
        }
    }
}
