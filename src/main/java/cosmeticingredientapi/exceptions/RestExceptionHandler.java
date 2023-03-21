package cosmeticingredientapi.exceptions;

import com.mysql.cj.exceptions.MysqlErrorNumbers;
import cosmeticingredientapi.records.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    private final ZoneId zoneId = ZoneId.of("Europe/Vilnius");

    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException exception
    ) {
        int exceptionErrorCode = exception.getErrorCode();
        String message = switch (exceptionErrorCode) {
            case MysqlErrorNumbers.ER_DUP_ENTRY -> "Duplicate entry can not be created";
            case MysqlErrorNumbers.ER_NO_REFERENCED_ROW_2 -> "Provided ID does not exist";
            default -> throw new IllegalStateException(
                    "Undefined error code in SQLIntegrityConstraintViolationException handler: " + exceptionErrorCode);
        };
        ZonedDateTime timestamp = ZonedDateTime.now(zoneId);
        return new ResponseEntity<>(new Error(message, timestamp), HttpStatus.BAD_REQUEST);
    }
}
