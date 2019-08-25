package exception;

public class PhoneNumberException extends Exception {
    private String wrongNumber;
    private String labelFr;

    public PhoneNumberException(String wrongNumber, String labelFr) {
        this.wrongNumber = wrongNumber;
        this.labelFr = labelFr;
    }

    @Override
    public String getMessage() {
        return
                "Le champ " + labelFr + " ne peut comporter que des chiffres et les caratères (+, -, ,/ , .)   \n" +
                        "Valeur encodée : " + wrongNumber;
    }
}
