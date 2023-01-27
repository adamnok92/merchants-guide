package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.Action;
import org.adamnok.merchant.model.handlers.actions.OutAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HandlerManagerTest {
    @Test
    void handleEmpty() {
        final var handler = new Handler() {

            @Override
            public Optional<String> pattern(ReadonlyState state) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Action action(Source source, ReadonlyState state) {
                throw new UnsupportedOperationException();
            }
            @Override
            public Optional<Action> handle(String message, ReadonlyState state) {
                return Optional.empty();
            }
        };
        final var handlerManager = new HandlerManager(List.of(handler));
        final var result = handlerManager.handle("TEST", null);
        final var expectedResult = new OutAction("I have no idea what you are talking about");
        assertEquals(expectedResult, result);
    }

    @Test
    void handleOut() {
        final var handler = new Handler() {

            @Override
            public Optional<String> pattern(ReadonlyState state) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Action action(Source source, ReadonlyState state) {
                throw new UnsupportedOperationException();
            }
            @Override
            public Optional<Action> handle(String message, ReadonlyState state) {
                return Optional.of(new OutAction("example result message"));
            }
        };
        final var handlerManager = new HandlerManager(List.of(handler));
        final var result = handlerManager.handle("TEST", null);
        final var expectedResult = new OutAction("example result message");
        assertEquals(expectedResult, result);
    }
}
