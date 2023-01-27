package org.adamnok.merchant.exceptions;

public final class InvalidForeignNumberException extends RuntimeException {
    private final String foreignNumber;

    public InvalidForeignNumberException(String foreignNumber) {
        super("Invalid foreign number: " + foreignNumber);
        this.foreignNumber = foreignNumber;
    }

    public String getForeignNumber() {
        return foreignNumber;
    }
}
