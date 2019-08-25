package exception;

public class DataAccessException extends Exception {
    private String source;
    public DataAccessException(String source) {
        this.source = source;
    }

    public String getMessage() {
        return "Une erreur est survenue lors de : " + source +
                "\n Contactez votre responsable informatique";
    }

}
