package exception;

public class StreetNumberException extends Exception {
    private String wrongStreetNumber;
    private String labelFr;

    public StreetNumberException(String wrongStreetNumber, String labelFr) {
        this.wrongStreetNumber = wrongStreetNumber;
        this.labelFr = labelFr;
    }

    @Override
    public String getMessage() {
        return
                "Le champ " + labelFr + " ne peut comporter que des chiffres (et une lettre facultative)\n" +
                        "Valeur encodée : " + wrongStreetNumber;
    }
}
