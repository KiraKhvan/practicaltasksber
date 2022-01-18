package project.controller.aspect.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.dto.response.ResponseDto;
import project.exception.BadRequestException;
import project.exception.NotFoundException;

@RestControllerAdvice(basePackages = "project.controller")
public class ExceptionHandlerController {

    protected static final Log log = LogFactory.getLog(ExceptionHandlerController.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDto> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(
                new ResponseDto(false, e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDto> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(
                new ResponseDto(false, e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleBadRequestException(Exception e) {
        int errorCode = (int) (500 + Math.random() * 99);
        String errorMsg = "Ошибка сервера № " + errorCode;
        log.error(errorMsg + "\n" + e.getMessage(), e);
        return new ResponseEntity<>(
                new ResponseDto(false, errorMsg),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
