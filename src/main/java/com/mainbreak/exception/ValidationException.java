package com.mainbreak.exception;

// Classe para ser usada quando uma validação ocorre um erro
public class ValidationException extends RuntimeException {

    public ValidationException(String msg) {
        super(msg);
    }

}
