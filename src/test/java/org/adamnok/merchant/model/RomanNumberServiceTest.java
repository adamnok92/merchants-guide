package org.adamnok.merchant.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumberServiceTest {
    private final RomanNumberService service = new RomanNumberService();

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 50, 100, 500, 100, 6, 3, 9, 1552})
    void numbers(int value) {
        final var result = service.fromRoman(service.toRoman(value));
        assertEquals(Optional.of(value), result);
    }

    @Test
    void invalidRomanNumber() {
        final var result = service.fromRoman("UJTO");
        assertTrue(result.isEmpty());
    }

    @Test
    void testNumber2006() {
        final var result = service.toRoman(2006);
        assertEquals("MMVI", result);
    }

    @Test
    void testRomanNumberMMVI() {
        final var result = service.fromRoman("MMVI");
        assertEquals(Optional.of(2006), result);
    }

    @Test
    void testNumber1944() {
        final var result = service.toRoman(1944);
        assertEquals("MCMXLIV", result);
    }

    @Test
    void testRomanNumberMCMXLIV() {
        final var result = service.fromRoman("MCMXLIV");
        assertEquals(Optional.of(1944), result);
    }
}
