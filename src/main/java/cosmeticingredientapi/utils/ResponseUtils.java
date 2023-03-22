package cosmeticingredientapi.utils;

import cosmeticingredientapi.records.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    private ResponseUtils() {

    }

    public static ResponseEntity<Object> createNotFoundByIdErrorResponseEntity(String entityName, Long id) {
        String message = String.format("%s with ID %s not found", entityName, id);
        return new ResponseEntity<>(
                new Error(message, TimeUtils.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> createMustNotBeNullResponseEntity(String propertyName) {
        String message = String.format("%s must not be null", propertyName);
        return new ResponseEntity<>(
                new Error(message, TimeUtils.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }
}