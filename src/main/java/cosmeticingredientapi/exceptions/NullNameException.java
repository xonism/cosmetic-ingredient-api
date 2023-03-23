package cosmeticingredientapi.exceptions;

public class NullNameException extends RuntimeException {
    public NullNameException(String entityName) {
        super(String.format("%s name must not be null", entityName));
    }
}
