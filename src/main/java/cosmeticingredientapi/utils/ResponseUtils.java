package cosmeticingredientapi.utils;

import cosmeticingredientapi.records.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    private ResponseUtils() {

    }

    public static ResponseEntity<Object> createNotFoundByIdResponse(String entityName) {
        String message = String.format("%s with provided ID not found", entityName);
        return new ResponseEntity<>(
                new Error(message, TimeUtils.getTimestamp()),
                HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<Object> createMustNotBeNullResponse(String propertyName) {
        String message = String.format("%s must not be null", propertyName);
        return new ResponseEntity<>(
                new Error(message, TimeUtils.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }
}
