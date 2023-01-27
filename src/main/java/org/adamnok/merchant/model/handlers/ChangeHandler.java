package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.exceptions.UnregisteredMaterialChangeException;
import org.adamnok.merchant.model.handlers.actions.Action;
import org.adamnok.merchant.model.handlers.actions.OutAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class ChangeHandler implements Handler {
    @Override
    public Optional<String> pattern(ReadonlyState state) {
        if (state.getAllForeignNumbers().isEmpty()) return Optional.empty();
        if (state.getAllMaterialNames().isEmpty()) return Optional.empty();

        final var materials = String.join("|", state.getAllMaterialNames());
        final var numbers = String.join("|", state.getAllForeignNumbers());
        return Optional.of(
            MessageFormat.format("how many ({0}) is (({1})+) ({2})\\?",
                materials,
                numbers,
                materials
            )
        );
    }

    @Override
    public Action action(Source source, ReadonlyState state) {
        final var fromMaterialName = source.get(3).asChars();
        final var toMaterialName = source.get(1).asChars();
        final var numberAsForeignText = source.get(2).asChars();
        final var number = state.getNumber(numberAsForeignText);

        final var change = state.getMaterialChange(fromMaterialName, toMaterialName)
            .orElseThrow(() -> new UnregisteredMaterialChangeException(fromMaterialName, toMaterialName));

        final var result = (number / change.fromValue()) * change.toValue();
        return new OutAction(
            MessageFormat.format("{0} {1} is {2} {3}",
                numberAsForeignText,
                fromMaterialName,
                result,
                toMaterialName
            )
        );
    }
}
