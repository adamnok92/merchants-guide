package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.StoreChangeAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.adamnok.merchant.repositories.Change;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
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
        when(state.getNumber("Beta Alpha")).thenReturn(6);
        final var source = Source.of(List.of("AB", "Beta Alpha", "Silver", "11", "Credits"));
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