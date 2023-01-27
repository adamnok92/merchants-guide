package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.Action;
import org.adamnok.merchant.model.handlers.actions.StoreChangeAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.adamnok.merchant.repositories.Change;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RegisterChangeHandler implements Handler {
    @Override
    public Optional<String> pattern(ReadonlyState state) {
        if (state.getAllForeignNumbers().isEmpty())
            return Optional.empty();
        final var allowedForeignNumbers = state.getAllForeignNumbers().stream()
            .sorted()
            .collect(Collectors.joining("|"));
        return Optional.of(
            MessageFormat.format("({0}) ({1}) is ({2}) ({3})",
                "(\s|" + allowedForeignNumbers + ")+",
                "[a-zA-Z]+",
                "[1-9][0-9]*|0",
                "[a-zA-Z]+"
            )
        );
    }

    @Override
    public Action action(Source source, ReadonlyState state) {
        return new StoreChangeAction(
            new Change(
                source.get(3).asChars(),
                source.get(5).asChars(),
                state.getNumber(source.get(1).asChars()),
                Integer.parseInt(source.get(4).asChars())
            )
        );
    }
}
