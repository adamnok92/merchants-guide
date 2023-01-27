package org.adamnok.merchant.model;

import org.adamnok.merchant.exceptions.InvalidForeignNumberException;
import org.adamnok.merchant.exceptions.InvalidRomanNumberException;
import org.adamnok.merchant.exceptions.UnregisteredMaterialChangeException;
import org.adamnok.merchant.model.handlers.HandlerManager;
import org.adamnok.merchant.model.handlers.actions.OutAction;
import org.adamnok.merchant.model.handlers.actions.StoreChangeAction;
import org.adamnok.merchant.model.handlers.actions.StoreForeignNumberAction;
import org.adamnok.merchant.model.state.StateService;
import org.adamnok.merchant.repositories.ChangeRepository;
import org.adamnok.merchant.repositories.ForeignNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Interpreter {
    @Autowired
    private StateService stateService;

    @Autowired
    private ChangeRepository changeRepository;

    @Autowired
    private ForeignNumberRepository foreignNumberRepository;

    @Autowired
    private HandlerManager handlerManager;

    public Optional<String> process(String message) {
        try {
            final var action = handlerManager.handle(message, stateService);
            if (action instanceof OutAction a) {
                return Optional.of(a.value());
            }
            if (action instanceof StoreForeignNumberAction a) {
                foreignNumberRepository.register(a.value());
                return Optional.empty();
            }
            if (action instanceof StoreChangeAction a) {
                changeRepository.register(a.value());
                return Optional.empty();
            }
            return Optional.of("I have no idea what you are talking about");
        } catch (InvalidForeignNumberException e) {
            return Optional.of(
                "Invalid number: " + e.getForeignNumber()
            );
        } catch (InvalidRomanNumberException e) {
            return Optional.of(
                "Invalid number: " + e.getRomanNumber()
            );
        } catch (UnregisteredMaterialChangeException e) {
            return Optional.of(
                "We do not have enough information about the next materials: " + e.getFromMaterialName() + " -> " + e.getToMaterialName()
            );
        }
    }

    public void startInteractive() {
        System.out.println("Merchant interpreter");
        final var scanner = new Scanner(System.in);
        System.out.print("> ");
        while (scanner.hasNextLine()) {
            process(scanner.nextLine()).ifPresent(System.out::println);
            System.out.print("> ");
        }
    }
}
