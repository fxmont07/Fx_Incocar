package userInterface;

import model.Vehicle;

import javax.swing.*;
import java.awt.*;

public class PanelDoc extends JPanel {
    // Attributs
    private JLabel documents;
    private JCheckBox hasImmatCertificate;
    private JCheckBox hasTechnicalControl;
    private JCheckBox hasConformityCertificate;

    // Constructors
    public PanelDoc(Vehicle vehicle) {
        // Layout
        setLayout(new GridLayout(1,3,2,2));

        documents = new JLabel("Documents :");
        documents.setHorizontalAlignment(SwingConstants.RIGHT);
        hasImmatCertificate = new JCheckBox("C. Immatriculation");
        hasTechnicalControl = new JCheckBox("Contrôle technique");
        hasConformityCertificate = new JCheckBox("C. Conformité");

        if(vehicle != null) {
            hasImmatCertificate.setSelected(vehicle.getHasImmatCertificate());
            hasTechnicalControl.setSelected(vehicle.getHasTechnicalControl());
            hasConformityCertificate.setSelected(vehicle.getHasConformityCertificate());
        }

        add(documents);
        add(hasImmatCertificate);
        add(hasTechnicalControl);
        add(hasConformityCertificate);
    }

    // Methods
    public boolean getHasImmatCertificate() {
        return hasImmatCertificate.isSelected();
    }

    public boolean getHasTechnicalControl() {
        return hasTechnicalControl.isSelected();
    }

    public boolean getHasConformityCertificate() {
        return hasConformityCertificate.isSelected();
    }
}
