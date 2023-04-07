package cosmeticingredientapi.exceptions;

public class IdMismatchException extends RuntimeException {
    public IdMismatchException() {
        super("Mismatched IDs were provided");
    }
}
