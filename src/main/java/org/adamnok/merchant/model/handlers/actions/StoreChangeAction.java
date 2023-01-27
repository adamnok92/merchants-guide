package org.adamnok.merchant.model.handlers.actions;

import org.adamnok.merchant.repositories.Change;

public record StoreChangeAction(
    Change value
) implements Action {
}
