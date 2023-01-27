package org.adamnok.merchant.repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ChangeRepository {
    private final List<Change> changes = new ArrayList<>();

    public void register(Change change) {
        changes.add(change);
    }

    public Optional<Change> get(String fromMaterialName, String toMaterialName) {
        final var materials = Set.of(fromMaterialName, toMaterialName);
        return changes.stream()
            .filter(it -> it.materialNames().containsAll(materials))
            .findFirst()
            .map(change -> {
                if (change.fromMaterialName().equals(fromMaterialName)) return change;
                else return change.reverse();
            });
    }

    public Set<String> getAllMaterialNames() {
        return changes.stream()
            .flatMap(it -> it.materialNames().stream())
            .collect(Collectors.toSet());
    }
}
