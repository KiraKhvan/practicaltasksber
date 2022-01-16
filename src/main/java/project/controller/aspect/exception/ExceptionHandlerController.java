package project.controller.aspect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.dto.response.ExceptionMessageDto;
import project.exception.BadRequestException;
import project.exception.NotFoundException;

@RestControllerAdvice(basePackages = "project.controller")
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessageDto> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionMessageDto(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessageDto> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(
                new ExceptionMessageDto(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessageDto> handleBadRequestException(Exception e) {
        return new ResponseEntity<>(
                new ExceptionMessageDto(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
