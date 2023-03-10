package org.adamnok.merchant.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeRepositoryTest {
    private ChangeRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ChangeRepository();
    }

    @Test
    void simpleRegisterAndGet() {
        final var change = new Change(
            "A",
            "B",
            3,
            2
        );
        repository.register(change);
        final var result = repository.get("A", "B");
        assertEquals(Optional.of(change), result);
    }

    @Test
    void simpleRegisterAndReversedGet() {
        final var change = new Change(
            "A",
            "B",
            3,
            2
        );
        final var expectedChange = new Change(
            "B",
            "A",
            2,
            3
        );
        repository.register(change);
        final var result = repository.get("B", "A");
        assertEquals(Optional.of(expectedChange), result);
    }

    @Test
    void unregisteredMaterial() {
        final var change = new Change(
            "A",
            "B",
            3,
            2
        );
        repository.register(change);
        final var result = repository.get("C", "B");
        assertEquals(Optional.empty(), result);
    }

    @Test
    void getAllMaterialNamesWithEmptyBody() {
        final var result = repository.getAllMaterialNames();
        assertEquals(Set.of(), result);
    }

    @Test
    void getAllMaterialNames() {
        repository.register(new Change("A", "B", 3, 2));
        repository.register(new Change("C", "B", 3, 2));
        repository.register(new Change("A", "D", 3, 2));
        final var result = repository.getAllMaterialNames();
        assertEquals(Set.of("A", "B", "C", "D"), result);
    }
}
