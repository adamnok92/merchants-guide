package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.Action;
import org.adamnok.merchant.model.state.ReadonlyState;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Handler {
    Optional<String> pattern(ReadonlyState state);

    Action action(Source source, ReadonlyState state);

    default Optional<Action> handle(String message, ReadonlyState state) {
        return pattern(state)
            .map(Pattern::compile)
            .map(it -> it.matcher(message))
            .filter(Matcher::find)
            .map(m -> new Source(message, m))
            .map(source -> action(source, state));
    }
}
