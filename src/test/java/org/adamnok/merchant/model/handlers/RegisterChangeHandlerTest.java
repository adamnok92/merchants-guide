package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.StoreChangeAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.adamnok.merchant.repositories.Change;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterChangeHandlerTest {

    private final RegisterChangeHandler handler = new RegisterChangeHandler();
    private ReadonlyState state;

    @BeforeEach
    void setUp() {
        state = mock(ReadonlyState.class);
    }

    @Test
    void pattern() {
        when(state.getAllForeignNumbers()).thenReturn(Set.of("Alpha", "Beta"));
        final var result = handler.pattern(state);
        final var expectedResult = "(( |Beta|Alpha)+) ([a-zA-Z]+) is ([1-9][0-9]*|0) ([a-zA-Z]+)";
        assertEquals(Optional.of(expectedResult), result);
    }

    @Test
    void action() {
        final var source = mock(Source.class);
        when(source.get(1)).thenReturn(new Source.SourceItem("Beta Alpha"));
        when(source.get(2)).thenReturn(new Source.SourceItem("Silver"));
        when(source.get(3)).thenReturn(new Source.SourceItem("11"));
        when(source.get(4)).thenReturn(new Source.SourceItem("Credits"));
        when(state.getNumber("Beta Alpha")).thenReturn(6);

        final var result = handler.action(source, state);
        final var expectedResult = new StoreChangeAction(
            new Change(
                "Silver",
                "Credits",
                6,
                11
            )
        );
        assertEquals(expectedResult, result);
    }
}