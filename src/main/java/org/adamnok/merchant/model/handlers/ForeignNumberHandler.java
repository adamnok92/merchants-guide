package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.Action;
import org.adamnok.merchant.model.handlers.actions.OutAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class ForeignNumberHandler implements Handler {
    @Override
    public Optional<String> pattern(ReadonlyState state) {
        if (state.getAllForeignNumbers().isEmpty()) return Optional.empty();
        final var numbers = String.join("|", state.getAllForeignNumbers());
        return Optional.of(
            MessageFormat.format("how much is (({0})+)\\?",
                "\s|" + numbers
            )
        );
    }

    @Override
    public Action action(Source source, ReadonlyState state) {
        final var number = state.getNumber(source.get(1).asChars());
        return new OutAction(
            MessageFormat.format("{0} is {1}",
                source.getValue(),
                number
            )
        );
    }
}
