package userInterface;

import com.toedter.calendar.JCalendar;
import controller.Controller;
import exception.DataAccessException;
import model.Bill;
import model.Vehicle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class WindowResearchBetweenDates extends JFrame {
    private Controller controller;
    private JCalendar firstDateCalendar;
    private JCalendar lastDateCalendar;
    private JPanel calendarsPanel;
    private JPanel firstCalendarPanel;
    private JPanel lastCalendarPanel;
    private JLabel firstDate;
    private JLabel lastDate;
    private String itemName;


    private JRadioButton isSale;
    private JRadioButton isPurchase;
    private ButtonGroup radioGroup;

    private JPanel buttons;
    private JButton valid;
    private JButton cancel;

    private JFrame mainRef;

    public WindowResearchBetweenDates(JFrame mainRef, String itemName){
        super(itemName.equals("Véhicules achetés entre 2 dates") ? "Recherche de véhicules" : "Recherche de facture");
        ButtonListener bListener;
        this.mainRef = mainRef;
        this.itemName = itemName;

        setBounds(300,100,300,460);
        setResizable(false);
        setLayout(new FlowLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });

        // Actions
        bListener = new ButtonListener();
        buttons = new JPanel();
        valid = new JButton("Rechercher");
        valid.addActionListener(bListener);
        cancel = new JButton("Annuler");
        cancel.addActionListener(bListener);

        buttons.add(valid);
        buttons.add(cancel);

        setCalendarsPanel();
        if(!itemName.equals("Véhicules achetés entre 2 dates")) {
            isSale = new JRadioButton("Ventes Incomplètes");
            isSale.setSelected(true);

            isPurchase = new JRadioButton("Achats");
            isPurchase.setSelected(false);
            radioGroup = new ButtonGroup();

            add(isSale);
            add(isPurchase);
            radioGroup.add(isSale);
            radioGroup.add(isPurchase);
        }

        add(buttons);
        controller = new Controller();

        setVisible(true);
    }


    public void setCalendarsPanel(){
        this.calendarsPanel = new JPanel();
        this.calendarsPanel.setLayout(new BorderLayout());
        this.firstCalendarPanel = new JPanel();
        this.firstCalendarPanel.setLayout(new FlowLayout());
        this.lastCalendarPanel = new JPanel();
        this.lastCalendarPanel.setLayout(new FlowLayout());

        this.firstDate = new JLabel("Entre : ");
        firstCalendarPanel.add(firstDate);
        this.firstDateCalendar = new JCalendar();
        firstCalendarPanel.add(firstDateCalendar);
        calendarsPanel.add(firstCalendarPanel,BorderLayout.NORTH);

        this.lastDate = new JLabel("Et : ");
        lastCalendarPanel.add(lastDate);
        this.lastDateCalendar = new JCalendar();
        lastCalendarPanel.add(lastDateCalendar);
        calendarsPanel.add(lastCalendarPanel,BorderLayout.SOUTH);

        add(calendarsPanel);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == valid) {
                if (firstDateCalendar.getDate().compareTo(lastDateCalendar.getDate()) <= 0) {
                    if (itemName.equals("Véhicules achetés entre 2 dates")) {
                        try {
                            ArrayList<Vehicle> vehicles = controller.getAllVehiclesBetweenDates(firstDateCalendar.getDate(), lastDateCalendar.getDate());
                            mainRef.getContentPane().removeAll();
                            mainRef.getContentPane().add(new PanelVehicle(controller, mainRef, vehicles));
                            mainRef.getContentPane().validate();
                            dispose();
                        } catch (DataAccessException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        try {
                            ArrayList<Bill> bills = controller.getBillsBetween2Dates(firstDateCalendar.getDate(), lastDateCalendar.getDate(), isSale.isSelected());
                            mainRef.getContentPane().removeAll();
                            mainRef.getContentPane().add(new PanelBill(controller, mainRef, bills));
                            mainRef.getContentPane().validate();
                            dispose();
                        } catch (DataAccessException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur dans les dates", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (event.getSource() == cancel) {
                    dispose();
                }
            }
        }
    }
}
