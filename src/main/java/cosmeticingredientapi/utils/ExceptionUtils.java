package cosmeticingredientapi.utils;

public class ExceptionUtils {

    private ExceptionUtils() {

    }

    @SuppressWarnings({"unchecked"})
    public static <E extends Exception> void throwActualException(Exception exception) throws E {
        throw (E) exception;
    }
}
