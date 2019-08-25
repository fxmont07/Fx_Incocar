package exception;

public class EnginePowerException extends Exception {
    private Integer enginePower;

    public EnginePowerException (Integer enginePower){
        this.enginePower = enginePower;
    }

    @Override
    public String getMessage(){
        return "La puissance moteur "+enginePower+" n'est pas valide.";
    }
}
