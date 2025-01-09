package org.ocanades.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(createRestResponseErrorMessage(HttpStatus.CONFLICT, ex));
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(createRestResponseErrorMessage(HttpStatus.NOT_FOUND, ex));
    }

    private RestResponseErrorMessage createRestResponseErrorMessage(HttpStatus status, Exception ex) {
        return RestResponseErrorMessage.builder()
                .type(ex.getClass().getSimpleName())
                .title(ex.getMessage())
                .status(status.value())
                .detail(ex.getLocalizedMessage())
                .instance("")
                .build();
    }

}
