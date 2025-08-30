package com.schmogel.isobar.infrastructure.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.schmogel.isobar.domain.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(
            NotFoundException ex, HttpServletRequest request) {
        var apiErrorMessage =
                new ApiErrorMessage(
                        HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage()), request.getRequestURI());
        return new ResponseEntity<>(apiErrorMessage, HttpStatus.NOT_FOUND);
    }
}
