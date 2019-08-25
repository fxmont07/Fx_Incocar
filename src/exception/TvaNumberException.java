package exception;

public class TvaNumberException extends Exception {
    private String wrongTva;
    private String labelFr;

    public TvaNumberException(String wrongTva, String labelFr) {
        this.wrongTva = wrongTva;
        this.labelFr = labelFr;
    }

    @Override
    public String getMessage(){
        return
                "Le champ " + labelFr + " ne peut comporter que des lettres puis des chiffres\n" +
                        "Valeur encodée : " + wrongTva;
    }
}
