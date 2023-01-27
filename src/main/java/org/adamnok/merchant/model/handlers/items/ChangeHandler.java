package org.adamnok.merchant.model.handlers.items;

import org.adamnok.merchant.exceptions.InvalidForeignNumberException;
import org.adamnok.merchant.exceptions.UnregisteredMaterialChangeException;
import org.adamnok.merchant.model.handlers.Handler;
import org.adamnok.merchant.model.handlers.Source;
import org.adamnok.merchant.model.handlers.actions.Action;
import org.adamnok.merchant.model.handlers.actions.OutAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChangeHandler implements Handler {
    @Override
    public Optional<String> pattern(ReadonlyState state) {
        if (state.getAllForeignNumbers().isEmpty()) return Optional.empty();
        if (state.getAllMaterialNames().isEmpty()) return Optional.empty();
        final var materials = state.getAllMaterialNames().stream()
            .sorted()
            .collect(Collectors.joining("|"));
        final var numbers = state.getAllForeignNumbers().stream()
            .sorted()
            .collect(Collectors.joining("|"));
        return Optional.of(
            MessageFormat.format("how many ({0}) is ((\s|{1})+) ({2}) \\?",
                materials,
                numbers,
                materials
            )
        );
    }

    @Override
    public Action action(Source source, ReadonlyState state) {
        final var fromMaterialName = source.get(4).asChars();
        final var toMaterialName = source.get(1).asChars();
        final var numberAsForeignText = source.get(2).asChars();
        final var number = state.getNumber(numberAsForeignText)
            .orElseThrow(() -> new InvalidForeignNumberException(numberAsForeignText));

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
