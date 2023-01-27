package org.adamnok.merchant.model;

import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;
import java.util.Set;

@Service
public class RomanNumberService {
    public static Set<Character> NUMBERS = Set.of('I', 'V', 'X', 'L', 'C', 'M');
    private final RomanNumeralFormat romanNumeralFormat = new RomanNumeralFormat();

    public Optional<Integer> fromRoman(String value) {
        try {
            return Optional.of(romanNumeralFormat.parse(value).intValue());
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    public String toRoman(int value) {
        return romanNumeralFormat.format(value);
    }
}
