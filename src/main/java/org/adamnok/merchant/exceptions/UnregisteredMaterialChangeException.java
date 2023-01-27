package org.adamnok.merchant.exceptions;

public class UnregisteredMaterialChangeException extends RuntimeException {
    private final String fromMaterialName;

    private final String toMaterialName;

    public UnregisteredMaterialChangeException(
        String fromMaterialName,
        String toMaterialName
    ) {
        super("Unregistered material change from " + fromMaterialName + " to " + toMaterialName);
        this.fromMaterialName = fromMaterialName;
        this.toMaterialName = toMaterialName;
    }

    public String getFromMaterialName() {
        return fromMaterialName;
    }

    public String getToMaterialName() {
        return toMaterialName;
    }
}
