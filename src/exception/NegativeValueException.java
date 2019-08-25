package exception;

public class NegativeValueException extends Exception{
    private double wrongValue;
    private String labelFr;

    public NegativeValueException(double wrongValue, String labelFr) {
        this.wrongValue = wrongValue;
        this.labelFr = labelFr;
    }

    @Override
    public String getMessage() {
        return
                "Le valeur de " + labelFr + " doit être positive\n" +
                        "Valeur encodée : " + wrongValue;
    }
}
