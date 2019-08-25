package userInterface;

import model.Vehicle;

import javax.swing.*;
import java.awt.*;

public class PanelPub extends JPanel {
    // Attributs
    private JLabel publications;
    private JCheckBox isSellingOnAutoscout;
    private JCheckBox isSellingOnFacebook;
    private JCheckBox isSellingOnSecondHand;

    // Constructors
    public PanelPub(Vehicle vehicle) {
        // Layout
        setLayout(new GridLayout(1,3,30,50));
        publications = new JLabel("Publications : ");
        isSellingOnAutoscout = new JCheckBox("Autoscout");
        isSellingOnFacebook = new JCheckBox("Facebook");
        isSellingOnSecondHand = new JCheckBox("2eMain");

        if(vehicle != null) {
            isSellingOnAutoscout.setSelected(vehicle.getIsSellingOnAutoscout());
            isSellingOnFacebook.setSelected(vehicle.getIsSellingOnFacebook());
            isSellingOnSecondHand.setSelected(vehicle.getIsSellingOnSecondHand());
        }

        add(publications);
        add(isSellingOnAutoscout);
        add(isSellingOnFacebook);
        add(isSellingOnSecondHand);
    }

    public boolean getIsSellingOnAutoscout() {
        return isSellingOnAutoscout.isSelected();
    }

    public boolean getIsSellingOnFacebook() {
        return isSellingOnFacebook.isSelected();
    }

    public boolean getIsSellingOnSecondHand() {
        return isSellingOnSecondHand.isSelected();
    }
}
