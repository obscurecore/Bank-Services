package bank.service.stonksservice.exception;

public class StonkNotFoundException extends RuntimeException {

    public StonkNotFoundException(String bucketId) {
        super("Stonk not found with id " + bucketId);
    }
}
