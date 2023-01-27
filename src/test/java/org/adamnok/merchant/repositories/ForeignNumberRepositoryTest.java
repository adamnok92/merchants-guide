package org.adamnok.merchant.repositories;

import org.adamnok.merchant.exceptions.InvalidForeignNumberException;
import org.adamnok.merchant.exceptions.InvalidRomanNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForeignNumberRepositoryTest {
    private ForeignNumberRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ForeignNumberRepository();
    }

    @Test
    void invalidGet() {
        assertThrows(InvalidForeignNumberException.class, () -> {
            repository.getRomanNumber(List.of("alpha"));
        });
    }

    @Test
    void invalidRomanNumber() {
        assertThrows(InvalidRomanNumberException.class, () -> {
            repository.register(new ForeignNumber("alpha", 'O'));
        });
    }

    @Test
    void simpleRegisterAndGet() {
        repository.register(new ForeignNumber("alpha", 'I'));
        final var result = repository.getRomanNumber(List.of("alpha"));
        assertEquals("I", result);
    }

    @Test
    void doubleRegisterAndGet() {
        repository.register(new ForeignNumber("alpha", 'I'));
        repository.register(new ForeignNumber("alpha", 'V'));
        final var result = repository.getRomanNumber(List.of("alpha"));
        assertEquals("V", result);
    }

    @Test
    void valuesRegisterAndGet() {
        repository.register(new ForeignNumber("alpha", 'I'));
        repository.register(new ForeignNumber("beta", 'X'));
        repository.register(new ForeignNumber("delta", 'C'));
        final var result = repository.getRomanNumber(List.of("alpha", "delta", "beta"));
        assertEquals("ICX", result);
    }
}
