package com.sokolov.core.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sokolov.core.domain.exception.ShortUrlException;
import com.sokolov.core.rest.model.ErrorApi;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ShortUrlException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorApi handle(ShortUrlException exception) {
        return buildError(exception);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorApi handle(Exception exception) {
        return buildError(exception);
    }

    private ErrorApi buildError(Exception exception) {
        String message = exception.getMessage();
        return new ErrorApi(message);
    }

}