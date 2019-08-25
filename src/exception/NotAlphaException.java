package exception;

public class NotAlphaException extends Exception {
    private String wrongString;
    private String labelFr;

    public NotAlphaException(String wrongString, String labelFr) {
        this.wrongString = wrongString;
        this.labelFr = labelFr;
    }

    @Override
    public String getMessage() {
        return
                "Le champ " + labelFr + "ne peut contenir que des lettres \n" +
                        "Valeur encodée : " + wrongString;
    }
}
