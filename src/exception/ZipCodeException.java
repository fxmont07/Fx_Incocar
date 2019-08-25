package exception;

public class ZipCodeException extends Exception {
    private String wrongString;
    private String labelFr;

    public ZipCodeException(String wrongString, String labelFr){
        this.labelFr = labelFr;
        this.wrongString = wrongString;
    }

    @Override
    public String getMessage(){
        return "Le champ " + labelFr + "doit respecter le format d'un Code Postal\n" +
                "Valeur encodée : " + wrongString;
    }
}
