package cosmeticingredientapi.exceptions;

import com.mysql.cj.exceptions.MysqlErrorNumbers;
import cosmeticingredientapi.records.Error;
import cosmeticingredientapi.utils.TimeUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<Error> handleSQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException exception
    ) {
        int exceptionErrorCode = exception.getErrorCode();
        String message = switch (exceptionErrorCode) {
            case MysqlErrorNumbers.ER_DUP_ENTRY -> "Duplicate entry can not be created";
            case MysqlErrorNumbers.ER_NO_REFERENCED_ROW_2 -> "Provided ID does not exist";
            case MysqlErrorNumbers.ER_BAD_NULL_ERROR -> "Provided invalid ID produced a null value";
            default -> throw new IllegalStateException(
                    "Undefined error code in SQLIntegrityConstraintViolationException handler: " + exceptionErrorCode);
        };

        return new ResponseEntity<>(
                new Error(message, TimeUtils.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Error> handleConstraintViolationException(
            ConstraintViolationException exception
    ) {
        List<String> exceptionMessages = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        return new ResponseEntity<>(
                new Error(exceptionMessages, TimeUtils.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IdMismatchException.class})
    public ResponseEntity<Error> handleIdMismatchException(
            IdMismatchException exception
    ) {
        return new ResponseEntity<>(
                new Error(exception.getMessage(), TimeUtils.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundByIdException.class})
    public ResponseEntity<Error> handleNotFoundByIdException(
            NotFoundByIdException exception
    ) {
        return new ResponseEntity<>(
                new Error(exception.getMessage(), TimeUtils.getTimestamp()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Error> handleHttpMessageNotReadableException() {
        String message = "Missing value in request body";
        return new ResponseEntity<>(
                new Error(message, TimeUtils.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<Error> handleEmptyResultDataAccessException() {
        String message = "Entity with provided ID not found";
        return new ResponseEntity<>(
                new Error(message, TimeUtils.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }
}
