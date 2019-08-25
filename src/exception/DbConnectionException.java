package exception;

public class DbConnectionException extends Exception {
    public String getMessage() {
        return "Erreur lors de la connexion avec la base de donnée";
    }
}
