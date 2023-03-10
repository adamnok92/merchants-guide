package org.adamnok.merchant.model.handlers;

import org.adamnok.merchant.model.handlers.actions.Action;
import org.adamnok.merchant.model.handlers.actions.OutAction;
import org.adamnok.merchant.model.state.ReadonlyState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HandlerManager {

    private final List<Handler> handlers;

    @Autowired
    public HandlerManager(List<Handler> handlers) {
        this.handlers = handlers;
    }

    public Action handle(String message, ReadonlyState state) {
        return handlers.stream()
            .map(handler -> handler.handle(message, state))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst()
            .orElse(new OutAction("I have no idea what you are talking about"));

    }
}
