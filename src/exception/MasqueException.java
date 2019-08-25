package exception;

public class MasqueException extends Exception {
    private String wrongString;
    private String labelFr;

    public MasqueException(String wrongString, String labelFr) {
        this.wrongString = wrongString;
        this.labelFr = labelFr;
    }

    @Override
    public String getMessage() {
        return
                "Le champ " + labelFr + " ne peut comporter que des lettres et les caractères suivant : ', , -\n" +
                        "Valeur encodée : " + wrongString;
    }
}
