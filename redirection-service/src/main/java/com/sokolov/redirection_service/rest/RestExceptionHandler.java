package com.sokolov.redirection_service.rest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sokolov.redirection_service.rest.model.ErrorDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleThrowable(Throwable exception) {
        if (exception instanceof ErrorResponse errorResponse) {
            return buildResponseEntity(errorResponse);
        }
        log.error("Unexpected error, cause:", exception);
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(errorDetails);
    }

    private ResponseEntity<ErrorDetails> buildResponseEntity(ErrorResponse errorResponse) {
        String errorDetail = errorResponse.getBody().getDetail();
        HttpStatusCode statusCode = errorResponse.getStatusCode();
        if (statusCode.is5xxServerError()) {
            log.error("Server error, message = {}", errorDetail);
        }
        return ResponseEntity.status(statusCode)
                .body(new ErrorDetails(errorDetail));
    }

}