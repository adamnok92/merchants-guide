package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.OutAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ForeignNumberHandlerTest {
    private final ForeignNumberHandler handler = new ForeignNumberHandler();
    private ReadonlyState state;

    @BeforeEach
    void setUp() {
        state = mock(ReadonlyState.class);
    }
    @Test
    void pattern() {
        when(state.getAllForeignNumbers()).thenReturn(Set.of("Alpha", "Beta"));
        final var result = handler.pattern(state);
        final var expectedResult = "how much is (( |Alpha|Beta)+)\\?";
        assertEquals(Optional.of(expectedResult), result);
    }

    @Test
    void action() {
        final var source = mock(Source.class);
        when(source.get(1)).thenReturn(new Source.SourceItem("Beta Alpha"));
        when(state.getNumber("Beta Alpha")).thenReturn(6);

        final var result = handler.action(source, state);
        final var expectedResult = new OutAction(
            MessageFormat.format("{0} is {1}",
                source.getValue(),
                6
            )
        );
        assertEquals(expectedResult, result);
    }
}