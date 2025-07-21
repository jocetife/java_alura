package com.alura.screenmatch.excepcion;

public class ErrorEnConversionDeDuracionException extends RuntimeException {
    private String message;
    public ErrorEnConversionDeDuracionException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
