package org.adamnok.merchant.model.state;

import org.adamnok.merchant.repositories.Change;

import java.util.Optional;
import java.util.Set;

public interface ReadonlyState {
    Set<String> getAllForeignNumbers();

    Set<String> getAllMaterialNames();

    String getRomanNumber(String foreignNumbers);

    int getNumber(String foreignNumbers);

    Optional<Change> getMaterialChange(String fromMaterialName, String toMaterialName);
}
