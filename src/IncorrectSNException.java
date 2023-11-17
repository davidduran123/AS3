public class IncorrectSNException extends Exception {
    public IncorrectSNException(long SN) {
        super("Serial-number value entered is not '"+ SN + "'. Please try again.");
    }
    public IncorrectSNException(String message){
        super(message);
    }
}
