package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.OutAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.adamnok.merchant.repositories.Change;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChangeHandlerTest {
    private final ChangeHandler handler = new ChangeHandler();
    private ReadonlyState state;

    @BeforeEach
    void setUp() {
        state = mock(ReadonlyState.class);
    }

    @Test
    void pattern() {
        when(state.getAllForeignNumbers()).thenReturn(Set.of("Alpha", "Beta"));
        when(state.getAllMaterialNames()).thenReturn(Set.of("Credits", "Silver"));
        final var result = handler.pattern(state);
        final var expectedResult = "how many (Credits|Silver) is ((Alpha|Beta)+) (Credits|Silver)\\?";
        assertEquals(Optional.of(expectedResult), result);
    }

    @Test
    void action() {
        final var source = mock(Source.class);
        when(source.get(1)).thenReturn(new Source.SourceItem("Credits"));
        when(source.get(2)).thenReturn(new Source.SourceItem("Alpha Beta"));
        when(source.get(3)).thenReturn(new Source.SourceItem("Silver"));
        when(state.getNumber("Alpha Beta")).thenReturn(7);
        when(state.getMaterialChange("Silver", "Credits")).thenReturn(
            Optional.of(
                new Change(
                    "Silver",
                    "Credits",
                    1,
                    10
                )
            )
        );

        final var result = handler.action(source, state);
        final var expectedResult = new OutAction("Alpha Beta Silver is 70 Credits]");
        assertEquals(expectedResult, result);
    }
}