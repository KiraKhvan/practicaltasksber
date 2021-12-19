package project.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionMessage(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessage> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(
                new ExceptionMessage(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleBadRequestException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionMessage(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
