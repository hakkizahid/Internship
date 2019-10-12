package obss.movietracker.error;

public class ExceptionResponse {
    private String message;
    private String httpCodeMessage;
    public ExceptionResponse(String message, String httpCodeMessage) {
        super();
        this.message = message;
        this.httpCodeMessage=httpCodeMessage;
    }
    public String getHttpCodeMessage() {
        return httpCodeMessage;
    }
    public String getMessage() {
        return message;
    }
}
