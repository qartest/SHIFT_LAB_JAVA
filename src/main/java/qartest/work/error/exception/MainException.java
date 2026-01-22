package qartest.work.error.exception;


public class MainException extends RuntimeException {
    protected String message;
    public MainException(String message) {
        this.message = message;
    }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
