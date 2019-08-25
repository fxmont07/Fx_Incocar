package exception;

public class AlphaNumericException extends Exception {
    private String string;

    public AlphaNumericException(String string){
        this.string = string;
    }

    @Override
    public String getMessage(){
        return "La valeur encodée doit être alphanumérique. La valeur que vous avez entrez est "+ string;
    }
}
