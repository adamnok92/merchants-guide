package org.adamnok.merchant.model.handlers.items;

import org.adamnok.merchant.model.handlers.Source;
import org.adamnok.merchant.model.handlers.actions.StoreForeignNumberAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.adamnok.merchant.repositories.ForeignNumber;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterForeignNumberHandlerTest {
    private final RegisterForeignNumberHandler handler = new RegisterForeignNumberHandler();

    @Test
    void pattern() {
        final var result = handler.pattern(null);
        assertEquals(Optional.of("([a-zA-Z]+) is ([CILMVX])"), result);
    }

    @Test
    void action() {
        final var source = mock(Source.class);
        when(source.get(1)).thenReturn(new Source.SourceItem("Alpha"));
        when(source.get(2)).thenReturn(new Source.SourceItem("B"));
        final var result = handler.action(source, null);
        final var expectedResult = new StoreForeignNumberAction(
            new ForeignNumber(
                "Alpha",
                'B'
            )
        );
        assertEquals(expectedResult, result);
    }
    @Test
    void handle() {
        final var message = "prok is V";
        final var state = mock(ReadonlyState.class);

        final var result = handler.handle(message, state);
        final var expectedResult = new StoreForeignNumberAction(
            new ForeignNumber(
                "prok",
                'V'
            )
        );
        assertEquals(Optional.of(expectedResult), result);
    }
}
