package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.RomanNumberService;
import org.adamnok.merchant.model.handlers.actions.Action;
import org.adamnok.merchant.model.handlers.actions.StoreForeignNumberAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.adamnok.merchant.repositories.ForeignNumber;

import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class RegisterForeignNumberHandler implements Handler {

    private static final String ROMAN_NUMBERS = RomanNumberService.NUMBERS
        .stream().map(Object::toString).sorted().collect(joining(""));

    public Optional<String> pattern(ReadonlyState state) {
        return Optional.of("([a-zA-Z]+) is ([" + ROMAN_NUMBERS + "])");
    }

    public Action action(Source source, ReadonlyState state) {
        return new StoreForeignNumberAction(
            new ForeignNumber(
                source.get(1).asChars(),
                source.get(2).asChars().charAt(0)
            )
        );
    }
}
