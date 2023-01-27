package org.adamnok.merchant.model.handlers.items;

import org.adamnok.merchant.model.handlers.Source;
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
        final var expectedResult = "(( |Alpha|Beta)+) ([a-zA-Z]+) is ([1-9][0-9]*|0) ([a-zA-Z]+)";
        assertEquals(Optional.of(expectedResult), result);
    }

    @Test
    void action() {
        final var source = mock(Source.class);
        when(source.get(1)).thenReturn(new Source.SourceItem("Beta Alpha"));
        when(source.get(3)).thenReturn(new Source.SourceItem("Silver"));
        when(source.get(4)).thenReturn(new Source.SourceItem("11"));
        when(source.get(5)).thenReturn(new Source.SourceItem("Credits"));
        when(state.getNumber("Beta Alpha")).thenReturn(Optional.of(6));

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

    @Test
    void handle() {
        final var message = "glob glob Silver is 34 Credits";
        final var state = mock(ReadonlyState.class);
        when(state.getAllForeignNumbers()).thenReturn(Set.of("glob"));
        when(state.getAllMaterialNames()).thenReturn(Set.of("Silver", "Credits"));
        when(state.getNumber("glob glob")).thenReturn(Optional.of(2));
        final var result = handler.handle(message, state);
        final var expectedResult = new StoreChangeAction(
            new Change(
                "Silver",
                "Credits",
                2,
                34
            )
        );
        assertEquals(Optional.of(expectedResult), result);
    }
}