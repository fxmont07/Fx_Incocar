package userInterface;

import controller.Controller;
import model.Client;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;

public class PanelDisplaySell extends JPanel {
    private JPanel vehiclePanel;
    private PanelModelManufacturer panelModelManufacturer;
    private PanelVehicleFeatures panelVehicleFeatures;

    private JPanel clientPanel;
    private PanelClient panelClient;

    public PanelDisplaySell(Controller controller, Vehicle vehicle, JFrame previousWindow) {
        setLayout(new BorderLayout());

        vehiclePanel = new JPanel();
        vehiclePanel.setLayout(new BorderLayout());
        panelModelManufacturer = new PanelModelManufacturer(controller, vehicle);
        panelModelManufacturer.lockPanel();
        panelVehicleFeatures = new PanelVehicleFeatures(vehicle);
        panelVehicleFeatures.hideState();
        panelVehicleFeatures.lockPanel();


        clientPanel = new JPanel();
        panelClient = new PanelClient(controller, previousWindow);

        vehiclePanel.add(panelModelManufacturer, BorderLayout.NORTH);
        vehiclePanel.add(panelVehicleFeatures, BorderLayout.CENTER);

        clientPanel.add(panelClient);


        add(vehiclePanel, BorderLayout.WEST);
        add(clientPanel, BorderLayout.EAST);

    }

    public Client getClientSelected() {
        return panelClient.getClient();
    }

    public boolean hasClientSelected() {
        return panelClient.hasClientSelected();
    }
}
