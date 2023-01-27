package org.adamnok.merchant.model.handlers.actions;

import org.adamnok.merchant.repositories.ForeignNumber;

public record StoreForeignNumberAction(
    ForeignNumber value
) implements Action {
}
