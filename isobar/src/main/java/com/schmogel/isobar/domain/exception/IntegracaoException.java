package com.schmogel.isobar.domain.exception;

public class IntegracaoException extends RuntimeException {

    public IntegracaoException(String message) {
        super(message);
    }

    public IntegracaoException(String message, Object... args) {
        super(String.format(message, args));
    }
}