package exception;

public class CheckNumberException extends Exception {
    private String checkNumber;

    public CheckNumberException(String checkNumber){
        this.checkNumber = checkNumber;
    }

    @Override
    public String getMessage(){
        return "Le numéro de chèque est invalide, il doit contenir 10 chiffres\nValeur encodée : " + checkNumber;
    }
}
