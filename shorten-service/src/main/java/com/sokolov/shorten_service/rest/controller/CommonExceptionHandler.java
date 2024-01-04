package com.sokolov.shorten_service.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sokolov.shorten_service.domain.exception.ShortenServiceException;
import com.sokolov.shorten_service.rest.model.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ShortenServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(ShortenServiceException exception) {
        return buildError(exception);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception) {
        log.error("Unexpected service error, cause:", exception);
        return buildError(exception);
    }

    private ErrorResponse buildError(Exception exception) {
        String message = exception.getMessage();
        return new ErrorResponse(message);
    }

}