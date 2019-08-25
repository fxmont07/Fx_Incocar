package model;

import exception.ChassisException;
import exception.EnginePowerException;
import exception.NegativeValueException;
import exception.NotAlphaException;
import tool.Tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Vehicle {
    // Attributs
    private String chassisNumber; // *
    private String color; // *

    private GregorianCalendar firstRegistractionDate;

    private Integer engineCylinder;
    private String engineEnergy;
    private Integer enginePower;

    private Double buyPrice;
    private GregorianCalendar buyDate;
    private Integer mileage;
    private String notes;

    private Boolean isOnSale;

    private Boolean hasImmatCertificate;
    private Boolean hasTechnicalControl;
    private Boolean hasConformityCertificate;

    private Boolean isSellingOnAutoscout;
    private Boolean isSellingOnFacebook;
    private Boolean isSellingOnSecondHand;

    private Model model; // *
    private Supplier supplier;
    private ArrayList<Bill> bills;

    //Constructors
    public Vehicle(String chassisNumber, String color, GregorianCalendar firstRegistractionDate,
                   Integer engineCylinder, String engineEnergy, Integer enginePower, Double buyPrice, GregorianCalendar buyDate, Integer mileage,
                   String notes, Boolean isOnSale,
                   Boolean hasImmatCertificate, Boolean hasTechnicalControl, Boolean hasConformityCertificate,
                   Boolean isSellingOnAutoscout, Boolean isSellingOnFacebook, Boolean isSellingOnSecondHand,
                   Model model, Supplier supplier, ArrayList<Bill> bills)
            throws NotAlphaException, ChassisException, NegativeValueException, EnginePowerException {
        setChassisNumber(chassisNumber);
        setColor(color);
        setFirstRegistractionDate(firstRegistractionDate);
        setEngineCylinder(engineCylinder);
        setEngineEnergy(engineEnergy);
        setEnginePower(enginePower);
        setBuyPrice(buyPrice);
        setBuyDate(buyDate);
        setMileage(mileage);
        setNotes(notes);
        setOnSale(isOnSale);
        setHasImmatCertificate(hasImmatCertificate);
        setHasTechnicalControl(hasTechnicalControl);
        setHasConformityCertificate(hasConformityCertificate);
        setIsSellingOnAutoscout(isSellingOnAutoscout);
        setIsSellingOnFacebook(isSellingOnFacebook);
        setIsSellingOnSecondHand(isSellingOnSecondHand);
        setModel(model);
        setSupplier(supplier);
        setBills(bills);
    }

    // Constructeur pour ajouter une véhicule venant d'un fournisseurs
    public Vehicle(String chassisNumber, String color, GregorianCalendar firstRegistractionDate, Integer engineCylinder, String engineEnergy,
                   Integer enginePower, Double buyPrice, GregorianCalendar buyDate, Integer mileage, String notes, Boolean isOnSale,
                   Boolean hasImmatCertificate, Boolean hasTechnicalControl, Boolean hasConformityCertificate, Model model, Supplier supplier)
            throws NotAlphaException, ChassisException, NegativeValueException, EnginePowerException {
        this(chassisNumber, color,firstRegistractionDate,engineCylinder, engineEnergy, enginePower, buyPrice, buyDate,mileage, notes,
                isOnSale, hasImmatCertificate,hasTechnicalControl, hasConformityCertificate,
                false,false,false,model,supplier, null);
    }

    // Constructeur pour modifier un vehicule
    public Vehicle(String chassisNumber, String color, GregorianCalendar firstRegistractionDate,
                   Integer engineCylinder, String engineEnergy, Integer enginePower, Double buyPrice, Integer mileage,
                   String notes, Boolean isOnSale,
                   Boolean hasImmatCertificate, Boolean hasTechnicalControl, Boolean hasConformityCertificate,
                   Boolean isSellingOnAutoscout, Boolean isSellingOnFacebook, Boolean isSellingOnSecondHand,
                   Model model, Supplier supplier, ArrayList<Bill> bills)
            throws NotAlphaException, ChassisException, NegativeValueException, EnginePowerException{
        this(chassisNumber, color,firstRegistractionDate,engineCylinder, engineEnergy, enginePower, buyPrice, null,mileage, notes,
                isOnSale, hasImmatCertificate,hasTechnicalControl, hasConformityCertificate,
                isSellingOnAutoscout,isSellingOnFacebook,isSellingOnSecondHand,model,supplier, bills);
    }


    //Getters & Setters
    public Double getBuyPrice() {
        return buyPrice;
    }

    public Integer getEnginePower(){
        return enginePower;
    }

    public Model getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }


    public String getChassisNumber() {
        return chassisNumber;
    }

    public GregorianCalendar getFirstRegistractionDate() {
        return firstRegistractionDate;
    }

    public String getFirstRegistrationInString() {
        if (firstRegistractionDate == null) return "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = firstRegistractionDate.getTime();

        return  dateFormat.format(date);
    }

    public String getBuyDateInString() {
        if (buyDate == null) return "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = buyDate.getTime();
        return  dateFormat.format(date);
    }
    public Integer getMileage() {
        return mileage;
    }

    public Integer getEngineCylinder() {
        return engineCylinder;
    }

    public String getEngineEnergy() {
        return engineEnergy;
    }

    public GregorianCalendar getBuyDate() {
        return buyDate;
    }

    public String getNotes() {
        return notes;
    }

    public Boolean getHasImmatCertificate() {
        return hasImmatCertificate;
    }

    public Boolean getHasConformityCertificate() {
        return hasConformityCertificate;
    }

    public Boolean getHasTechnicalControl() {
        return hasTechnicalControl;
    }

    public Boolean getIsSellingOnAutoscout() {
        return isSellingOnAutoscout;
    }

    public Boolean getIsSellingOnFacebook() {
        return isSellingOnFacebook;
    }

    public Boolean getIsSellingOnSecondHand() {
        return isSellingOnSecondHand;
    }

    public int getIndiceEnergy(String string) {
        if(string.equals("Essence")) return 0; // Lisibilité
        if(string.equals("Diesel")) return 1;
        if(string.equals("Electrique")) return 2;
        return -1;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setChassisNumber(String chassisNumber) throws ChassisException {
        if(!Tool.isValidChassisNumber(chassisNumber)) {
            throw new ChassisException(chassisNumber, "Numéro de chassis");
        }
        this.chassisNumber = chassisNumber;
    }

    public void setColor(String color) throws NotAlphaException {
        if(!Tool.isAlpha(color)) {
            throw new NotAlphaException(color, "Couleur");
        }
        color.toUpperCase();
        this.color = color;
    }

    public void setBuyDate(GregorianCalendar buyDate) {
        this.buyDate = buyDate;
    }

    public void setEngineCylinder(Integer engineCylinder) throws NegativeValueException {
        if (engineCylinder != null) {
            if(engineCylinder < 0) {
                throw new NegativeValueException(engineCylinder, "Cylindrée");
            }
        }
        this.engineCylinder = engineCylinder;
    }

    public void setEngineEnergy(String engineEnergy) {
        this.engineEnergy = engineEnergy;
    }

    public void setEnginePower(Integer enginePower) throws EnginePowerException {
        if(enginePower != null){
            if(!Tool.isValidEnginePower(enginePower)) throw new EnginePowerException(enginePower);
        }
        this.enginePower = enginePower;
    }



    public void setFirstRegistractionDate(GregorianCalendar firstRegistractionDate) {
        this.firstRegistractionDate = firstRegistractionDate;
    }

    public void setMileage(Integer mileage) throws NegativeValueException {
        if (mileage != null) {
            if (mileage < 0) {
                throw new NegativeValueException(mileage, "Kilomètrage");
            }
        }
        this.mileage = mileage;
    }

    public void setBuyPrice(Double buyPrice) throws NegativeValueException{
        if (buyPrice != null) {
            if(buyPrice < 0) {
                throw new NegativeValueException(buyPrice, "Prix d'achat");
            }
        }
        this.buyPrice = buyPrice;
    }

    public void setNotes(String notes) {
        if(notes != null) {
            notes.toLowerCase();
            notes.substring(0, 1).toUpperCase();
        }
        this.notes = notes;
    }

    public void setHasConformityCertificate(Boolean hasConformityCertificate) {
        this.hasConformityCertificate = hasConformityCertificate;
    }

    public void setHasTechnicalControl(Boolean hasTechnicalControl) {
        this.hasTechnicalControl = hasTechnicalControl;
    }

    public void setHasImmatCertificate(Boolean hasImmatCertificate) {
        this.hasImmatCertificate = hasImmatCertificate;
    }

    public void setIsSellingOnAutoscout(Boolean isSellingOnAutoscout) {
        this.isSellingOnAutoscout = isSellingOnAutoscout;
    }

    public void setIsSellingOnSecondHand(Boolean isSellingOnSecondHand) {
        this.isSellingOnSecondHand = isSellingOnSecondHand;
    }

    public void setIsSellingOnFacebook(Boolean isSellingOnFacebook) {
        this.isSellingOnFacebook = isSellingOnFacebook;
    }

    public void setModel(Model model){this.model = model;}

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    //Methods
    public Bill getLastBill() {
        if(isOnSale) {
            return null;
        }
        return bills.get(bills.size()-1);
    }

    public void setVehicleBills() {
        for(Bill bill : bills) {
            bill.setVehicle(this);
        }
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

}
