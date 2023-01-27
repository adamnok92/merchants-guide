package org.adamnok.merchant.exceptions;

public class UnregisteredMaterialChangeException extends RuntimeException {
    public UnregisteredMaterialChangeException(
        String fromMaterialName,
        String toMaterialName
    ) {
        super("Unregistered material change from " + fromMaterialName + " to " + toMaterialName);
    }
}
