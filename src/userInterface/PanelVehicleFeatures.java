package userInterface;

import model.Vehicle;
import tool.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.GregorianCalendar;

public class PanelVehicleFeatures extends JPanel {
    private Vehicle vehicle;

    private ButtonGroup radioGroup;
    private JRadioButton isOnSale;
    private JRadioButton isSold;

    private JLabel chassisNumber;
    private JTextField inputChassisNumber;

    private JLabel color;
    private JTextField inputColor;

    private JLabel firstRegistrationDate;
    private JTextField inputFirstRegistrationDate;

    private JLabel energy;
    private static String[] energies = {"Essence", "Diesel", "Electrique"};
    private JComboBox energiesList;

    private JLabel engineCylinder;
    private JTextField inputEngineCylinder;

    private JLabel enginePower;
    private JTextField inputEnginePower;

    private JLabel mileage;
    private JTextField inputMileage;

    private JLabel notes;
    private JTextArea inputNotes;

    private JLabel buyPrice;
    private JTextField inputBuyPrice;

    public PanelVehicleFeatures(Vehicle vehicle) {
        FocListener action = new FocListener();
        ImageIcon icon = new ImageIcon(".\\.\\img\\settings.png");
        setLayout(new GridLayout(10, 3, 5, 5));
        this.vehicle = vehicle;

        radioGroup = new ButtonGroup();
        isOnSale = new JRadioButton("En Vente", true);
        isOnSale.setHorizontalAlignment(SwingConstants.RIGHT);
        isSold = new JRadioButton("Vendue", false);


        chassisNumber = new JLabel("Numéro de chassis* :");
        chassisNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        chassisNumber.setIcon(icon);
        chassisNumber.setToolTipText("Le numéro de chassis doit contenir les 17 caractères (A-Z, 0-9)");
        inputChassisNumber = new JTextField();
        inputChassisNumber.addFocusListener(action);

        color = new JLabel("Couleur :");
        color.setHorizontalAlignment(SwingConstants.RIGHT);
        color.setIcon(icon);
        color.setToolTipText("La couleur doit contenir que des lettres (A-Z)");
        inputColor = new JTextField();
        inputColor.addFocusListener(action);

        firstRegistrationDate = new JLabel("Date de la première immatriculation :");
        firstRegistrationDate.setHorizontalAlignment(SwingConstants.RIGHT);
        firstRegistrationDate.setIcon(icon);
        firstRegistrationDate.setToolTipText("La date doit être au format JJ/MM/AAAA");
        inputFirstRegistrationDate = new JTextField();
        inputFirstRegistrationDate.addFocusListener(action);


        energy = new JLabel("Energies : ");
        energy.setHorizontalAlignment(SwingConstants.RIGHT);
        energiesList = new JComboBox(energies);

        engineCylinder = new JLabel("Cylindrée :");
        engineCylinder.setHorizontalAlignment(SwingConstants.RIGHT);
        engineCylinder.setIcon(icon);
        engineCylinder.setToolTipText("La cylindrée doit être positive");
        inputEngineCylinder = new JTextField();
        inputEngineCylinder.addFocusListener(action);

        enginePower = new JLabel("Puissance (kw) :");
        enginePower.setHorizontalAlignment(SwingConstants.RIGHT);
        enginePower.setIcon(icon);
        enginePower.setToolTipText("La puissance doit être positive");
        inputEnginePower = new JTextField();
        inputEnginePower.addFocusListener(action);

        mileage = new JLabel("Kilomètrage :");
        mileage.setHorizontalAlignment(SwingConstants.RIGHT);
        mileage.setIcon(icon);
        mileage.setToolTipText("Le kilomètrage doit être positif");
        inputMileage = new JTextField();
        inputMileage.addFocusListener(action);

        notes = new JLabel("Commentaires :");
        notes.setHorizontalAlignment(SwingConstants.RIGHT);
        inputNotes = new JTextArea();
        inputNotes.setColumns(20);

        buyPrice = new JLabel("Prix d'achat :");
        buyPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        buyPrice.setIcon(icon);
        buyPrice.setToolTipText("Le prix d'achat doit être positif");
        inputBuyPrice = new JTextField();
        inputBuyPrice.addFocusListener(action);


        if (vehicle != null) {
            if(vehicle.getOnSale()) {
                isOnSale.setSelected(true);
                isSold.setSelected(false);
            } else {

                isOnSale.setSelected(false);
                isSold.setSelected(true);
            }
            inputColor.setText(vehicle.getColor());
            inputChassisNumber.setText(vehicle.getChassisNumber());
            inputMileage.setText(vehicle.getMileage() == 0 ? "" : Integer.toString(vehicle.getMileage()));
            inputFirstRegistrationDate.setText(vehicle.getFirstRegistrationInString());
            energiesList.setSelectedIndex(vehicle.getIndiceEnergy(vehicle.getEngineEnergy()));
            inputEngineCylinder.setText(vehicle.getEngineCylinder() == 0 ? "" : Integer.toString(vehicle.getEngineCylinder()));
            inputEnginePower.setText(vehicle.getEnginePower() == 0 ? "" : Integer.toString(vehicle.getEnginePower()));
            inputBuyPrice.setText(vehicle.getBuyPrice() == 0 ? "" : Double.toString(vehicle.getBuyPrice()) );
            inputNotes.setText(vehicle.getNotes());
            inputChassisNumber.setEditable(false);
        } else {
            hideState();
        }

        add(isOnSale);
        add(isSold);
        radioGroup.add(isOnSale);
        radioGroup.add(isSold);

        add(chassisNumber);
        add(inputChassisNumber);

        add(color);
        add(inputColor);

        add(firstRegistrationDate);
        add(inputFirstRegistrationDate);

        add(energy);
        add(energiesList);

        add(engineCylinder);
        add(inputEngineCylinder);

        add(enginePower);
        add(inputEnginePower);

        add(mileage);
        add(inputMileage);

        add(notes);
        add(inputNotes);

        add(buyPrice);
        add(inputBuyPrice);
    }


    // Methods
    public boolean getIsOnSale() {
        return isOnSale.isSelected();
    }

    public String getChassisNumber() {
        return inputChassisNumber.getText();
    }
    public String getColor() {
        return inputColor.getText();
    }

    public GregorianCalendar getFirstDateRegistion(){
        if(inputFirstRegistrationDate.getText().isEmpty()) return null;
        String [] dateSplit = inputFirstRegistrationDate.getText().split("/");
        return new GregorianCalendar(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1])-1, Integer.parseInt(dateSplit[0])+1);
    }
    public Integer getMileage() {
        if(inputMileage.getText().isEmpty()) return null;
        return Integer.parseInt(inputMileage.getText());
    }
    public Integer getEngineCylinder(){
        if(inputEngineCylinder.getText().isEmpty()) return null;
        return Integer.parseInt(inputEngineCylinder.getText());
    }
    public String getEngineEnergy() {
        return (String)energiesList.getSelectedItem();
    }
    public Integer getEnginePower() {
        if(inputEnginePower.getText().isEmpty()) return null;
        return Integer.parseInt(inputEnginePower.getText());
    }

    public Double getBuyPrice() {
        if(inputBuyPrice.getText().isEmpty()) return null;
        return Double.parseDouble(inputBuyPrice.getText());
    }

    public String getNotes() {
        if(inputNotes.getText().isEmpty()) return null;
        return inputNotes.getText();
    }

    public boolean isFormValid() {
        if(!Tool.isValidChassisNumber(getChassisNumber())) return false;
        else if (!Tool.isAlpha(getColor()) && !getColor().isEmpty()) return false;
        else if (getMileage() != null && !Tool.isPositiveNumber(getMileage())) return false;
        else if(!Tool.isDate(inputFirstRegistrationDate.getText()) && !inputFirstRegistrationDate.getText().isEmpty()) return false;
        else if(!Tool.isPositiveNumber(inputEngineCylinder.getText()) && !inputEngineCylinder.getText().isEmpty()) return false;
        else if(!Tool.isPositiveNumber(inputEnginePower.getText()) && !inputEnginePower.getText().isEmpty())return false;
        else if(!Tool.isOnlyNumber(inputBuyPrice.getText()) && !inputBuyPrice.getText().isEmpty()) return false;
        return true;
    }

    public void hideState() {
        isOnSale.setVisible(false);
        isSold.setVisible(false);
    }

    public void lockPanel() {
        inputColor.setEditable(false);
        inputFirstRegistrationDate.setEditable(false);
        energiesList.setEnabled(false);
        inputEngineCylinder.setEditable(false);
        inputEnginePower.setEditable(false);
        inputMileage.setEditable(false);
        inputNotes.setEditable(false);
        inputBuyPrice.setEditable(false);
    }

    // Class
    public class FocListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent focusEvent) {
        }

        public void focusLost(FocusEvent focusEvent) {
            // Valide quand bien remplit
            if (focusEvent.getSource() == inputChassisNumber) {
                if (Tool.isValidChassisNumber(inputChassisNumber.getText())) {
                    inputChassisNumber.setBackground(Color.WHITE);
                } else {
                    inputChassisNumber.setBackground(Tool.RED);
                }
            } else {
                if (focusEvent.getSource() == inputColor) {
                    if (Tool.isAlpha(inputColor.getText()) || inputColor.getText().isEmpty()) {
                        inputColor.setBackground(Color.WHITE);
                    } else {
                        inputColor.setBackground(Tool.RED);
                    }
                } else {
                    if (focusEvent.getSource() == inputMileage) {
                        if (Tool.isOnlyNumber(inputMileage.getText()) || inputMileage.getText().isEmpty()) {
                            inputMileage.setBackground(Color.WHITE);
                        } else {
                            inputMileage.setBackground(Tool.RED);
                        }
                    } else {
                        if (focusEvent.getSource() == inputFirstRegistrationDate) {
                            if (Tool.isDate(inputFirstRegistrationDate.getText()) || inputFirstRegistrationDate.getText().isEmpty()) {
                                inputFirstRegistrationDate.setBackground(Color.WHITE);
                            } else {
                                inputFirstRegistrationDate.setBackground(Tool.RED);
                            }
                        } else {
                            if (focusEvent.getSource() == inputEngineCylinder) {
                                if (Tool.isOnlyNumber(inputEngineCylinder.getText()) || inputEngineCylinder.getText().isEmpty()) {
                                    inputEngineCylinder.setBackground(Color.WHITE);
                                } else {
                                    inputEngineCylinder.setBackground(Tool.RED);
                                }
                            } else {
                                if (focusEvent.getSource() == inputEnginePower) {
                                    if (Tool.isOnlyNumber(inputEnginePower.getText()) || inputEnginePower.getText().isEmpty()) {
                                        inputEnginePower.setBackground(Color.WHITE);
                                    } else {
                                        inputEnginePower.setBackground(Tool.RED);
                                    }
                                } else {
                                    if (focusEvent.getSource() == inputBuyPrice) {
                                        if (Tool.isOnlyNumber(inputBuyPrice.getText()) || inputBuyPrice.getText().isEmpty()) {
                                            inputBuyPrice.setBackground(Color.WHITE);
                                        } else {
                                            inputBuyPrice.setBackground(Tool.RED);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

