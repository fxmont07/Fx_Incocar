package exception;

public class ChassisException extends Exception {
    private String wrongChassis;
    private String labelFr;

    public ChassisException(String wrongChassis, String labelFr) {
        this.wrongChassis = wrongChassis;
        this.labelFr = labelFr;
    }

    @Override
    public String getMessage() {
        return
                "Le " + labelFr + " doit possèder 17 caractères\n " +
                        "Valeur encodée : " + wrongChassis;
    }
}
