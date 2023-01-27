package org.adamnok.merchant.model.handlers;

import java.util.regex.Matcher;

final class SourceImpl implements Source {
    private final Matcher matcher;

    public SourceImpl(Matcher matcher) {
        this.matcher = matcher;
    }

    public Source.SourceItem get(int index) {
        return new Source.SourceItem(this.matcher.group(index));
    }

}
