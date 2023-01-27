package org.adamnok.merchant.model.handlers;

import java.util.regex.Matcher;

public class Source {
    private final Matcher matcher;

    public Source(Matcher matcher) {
        this.matcher = matcher;
    }

    public Source.SourceItem get(int index) {
        return new Source.SourceItem(this.matcher.group(index));
    }

    public final static class SourceItem {
        private final String value;

        public SourceItem(String value) {
            this.value = value;
        }

        public String asChars() {
            return value;
        }
    }
}
