package org.adamnok.merchant.exceptions;

public final class InvalidRomanNumberException extends RuntimeException {
    private final char romanNumber;

    public InvalidRomanNumberException(char romanNumber) {
        super("Invalid roman number: " + romanNumber);
        this.romanNumber = romanNumber;
    }

    public char getRomanNumber() {
        return romanNumber;
    }
}
