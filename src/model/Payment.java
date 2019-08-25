package model;

import exception.CheckNumberException;
import exception.NegativeValueException;
import tool.Tool;

public class Payment {
    private String type;
    private Double amount;
    private String checkNumber;
    private Bill bill;

    public Payment(String type, Double amount, String checkNumber, Bill bill)throws Exception{
        setType(type);
        setAmount(amount);
        setCheckNumber(checkNumber);
        setBill(bill);
    }

    public Payment(String type, Double amount, String checkNumber) throws Exception{
        this(type, amount, checkNumber, null);
    }



    public void setType(String type){
        //Pas besoin de faire de vérification, ça sera surement un Radio Button
        this.type = type;
    }

    public void setAmount(Double amount) throws NegativeValueException{
        if(amount > 0) this.amount = amount;
        else throw new NegativeValueException(amount,"montant du payement");
    }

    public void setCheckNumber(String checkNumber)throws CheckNumberException {
        if(checkNumber != null) {
            if(!Tool.isValidCheckNumber(checkNumber)) throw new CheckNumberException(checkNumber);
        }
        this.checkNumber = checkNumber;
    }


    public void setBill (Bill bill) {
        this.bill = bill;
    }

    public String getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public Bill getSale() {
        return bill;
    }

    public String getCheckNumber() {
        return checkNumber;
    }
}
