package org.adamnok.merchant.repositories;

import org.adamnok.merchant.exceptions.InvalidForeignNumberException;
import org.adamnok.merchant.exceptions.InvalidRomanNumberException;
import org.adamnok.merchant.model.RomanNumberService;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public final class ForeignNumberRepository {
    private final Map<String, Character> numbers = new HashMap<>();

    public Set<String> getAllForeignNumbers() {
        return numbers.keySet();
    }

    public void register(ForeignNumber number) {
        if (!RomanNumberService.NUMBERS.contains(number.romanNumber())) {
            throw new InvalidRomanNumberException(number.romanNumber());
        }
        numbers.put(number.name(), number.romanNumber());
    }

    private char getRomanNumber(String foreignNumber) {
        if (!numbers.containsKey(foreignNumber)) {
            throw new InvalidForeignNumberException(foreignNumber);
        }
        return numbers.get(foreignNumber);
    }

    public String getRomanNumber(List<String> foreignNumbers) {
        return foreignNumbers.stream()
            .map(this::getRomanNumber)
            .map(Object::toString)
            .collect(Collectors.joining(""));
    }
}
