package org.adamnok.merchant.model.state;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public interface ReadonlyState {
    Set<String> getAllForeignNumbers();

    String getRomanNumber(String foreignNumbers);

    int getNumber(String foreignNumbers);
}
