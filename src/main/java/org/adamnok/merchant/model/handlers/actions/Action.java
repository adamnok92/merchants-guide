package org.adamnok.merchant.model.handlers.actions;

public sealed interface Action
    permits OutAction, StoreChangeAction, StoreForeignNumberAction {
}
