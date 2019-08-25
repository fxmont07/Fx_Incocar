package model;

import tool.Tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Bill {
    // Attributs
    private Integer id;
    private Boolean isSale;
    private Double billPrice;
    private GregorianCalendar billDate;
    private Vehicle vehicle;
    private Client client;
    private ArrayList<Payment> payments;

    //Constructors // TODO
    public Bill(Boolean isSale, Double billPrice, GregorianCalendar billDate, Vehicle vehicle, Client client) {
        this.isSale = isSale;
        this.billDate = billDate;
        this.billPrice = billPrice;
        this.vehicle = vehicle;
        this.client = client;
    }

    // Lors d'une vente date du jour
    public Bill(Boolean isSale, Double billPrice, Vehicle vehicle, Client client) {
        this(isSale, billPrice, new GregorianCalendar(), vehicle, client);
    }

    // Lors du chargement table facture il faut l'id
    public Bill(Boolean isSale, Double billPrice, GregorianCalendar billDate, Vehicle vehicle, Client client, Integer id) {
        this(isSale, billPrice, billDate, vehicle, client); {
            this.id = id;
        }
    }

    public Bill(Integer id, Boolean isSale, Double billPrice, GregorianCalendar billDate, Vehicle vehicle, Client client, ArrayList<Payment> payments) {
        this.id = id;
        this.isSale = isSale;
        this.billPrice = billPrice;
        this.billDate = billDate;
        this.vehicle = vehicle;
        this.client = client;
        this.payments = payments;
    }

    public Integer getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Boolean isSale() {
        return isSale;
    }

    public Double getBillPrice() {
        return billPrice;
    }

    public String getBillDateInString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = billDate.getTime();
        return  dateFormat.format(date);
    }

    public GregorianCalendar getBillDate() {
        return billDate;
    }

    public Client getClient() {
        return client;
    }

    public void setSale(Boolean sale) {
        isSale = sale;
    }

    public void setBillPrice(Double billPrice) {
        if(Tool.isPositiveDouble(billPrice)) {
            this.billPrice = billPrice;
        }
    }

    public void setBillDate(GregorianCalendar billDate) {
        this.billDate = billDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // Methods
    public double sumPayments() {
        if(payments == null) return 0;
        double sum = 0;
        for (Payment payment : payments) {
            sum += payment.getAmount();
        }
        return sum;
    }

    public void setPayments() {
        for (Payment payment : payments) {
            payment.setBill(this);
        }
    }
}
