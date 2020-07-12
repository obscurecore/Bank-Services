package bank.service.stonksservice.payload;

/**
 * The type Error response.
 * @author Ruslan Potapov
 */
public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
