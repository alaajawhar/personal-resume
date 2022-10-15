package com.freelancing.exceptionHandlers;

import com.freelancing.exceptions.ClientException;
import com.freelancing.models.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Alaa jawhar
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleUnexpectedException(Exception ex, WebRequest request) {
        log.error("Global Exception has been caught", ex);
        return handleExceptionInternal(ex,
                new ErrorResponse("Unexpected Error Happened"),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ClientException.class})
    protected ResponseEntity<Object> handleClientException(ClientException ex, WebRequest request) {
        log.error("ClientException has been caught");
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
