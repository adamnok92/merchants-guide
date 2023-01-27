package org.adamnok.merchant.repositories;

import java.util.Set;

public record Change(
    String fromMaterialName,
    String toMaterialName,
    int fromValue,
    int toValue
) {
    public Set<String> materialNames() {
        return Set.of(fromMaterialName, toMaterialName);
    }

    public Change reverse() {
        return new Change(
            toMaterialName,
            fromMaterialName,
            toValue,
            fromValue
        );
    }
}
