package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.Action;
import org.adamnok.merchant.model.handlers.actions.OutAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {

    @Test
    void handleEmpty() {
        final var handler = new Handler() {

            @Override
            public Optional<String> pattern(ReadonlyState state) {
                return Optional.empty();
            }

            @Override
            public Action action(Source source, ReadonlyState state) {
                return null;
            }
        };
        final var result = handler.handle("TEST", null);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void handleSimple() {
        final var handler = new Handler() {

            @Override
            public Optional<String> pattern(ReadonlyState state) {
                return Optional.of("TEST");
            }

            @Override
            public Action action(Source source, ReadonlyState state) {
                return new OutAction("test-message");
            }
        };
        final var result = handler.handle("TEST", null);
        assertEquals(Optional.of(new OutAction("test-message")), result);
    }

    @Test
    void handleSimpleNonPattern() {
        final var handler = new Handler() {

            @Override
            public Optional<String> pattern(ReadonlyState state) {
                return Optional.of("TEST2");
            }

            @Override
            public Action action(Source source, ReadonlyState state) {
                return new OutAction("test-message");
            }
        };
        final var result = handler.handle("TEST", null);
        assertEquals(Optional.empty(), result);
    }
}
