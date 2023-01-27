package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.StoreForeignNumberAction;
import org.adamnok.merchant.repositories.ForeignNumber;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegisterForeignNumberHandlerTest {
    private final RegisterForeignNumberHandler handler = new RegisterForeignNumberHandler();

    @Test
    void pattern() {
        final var result = handler.pattern(null);
        assertEquals(Optional.of("([a-zA-Z]+) is ([CILMVX])"), result);
    }

    @Test
    void action() {
        final var source = Source.of(List.of("AB", "A", "B"));
        final var result = handler.action(source, null);
        final var expectedResult = new StoreForeignNumberAction(
            new ForeignNumber(
                "A",
                'B'
            )
        );
        assertEquals(expectedResult, result);
    }
}
