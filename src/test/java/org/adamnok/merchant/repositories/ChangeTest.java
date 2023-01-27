package org.adamnok.merchant.repositories;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ChangeTest {

    @Test
    void materialNames() {
        final var change = new Change(
            "A",
            "B",
            3,
            2
        );
        final var result = change.materialNames();
        assertEquals(Set.of("A", "B"), result);
    }

    @Test
    void reverse() {
        final var change = new Change(
            "A",
            "B",
            3,
            2
        );
        final var reversed = new Change(
            "B",
            "A",
            2,
            3
        );
        final var result = change.reverse();
        assertEquals(reversed, result);
    }
}