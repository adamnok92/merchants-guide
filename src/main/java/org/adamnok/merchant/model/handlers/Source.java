package org.adamnok.merchant.model.handlers;

import java.util.List;
import java.util.regex.Matcher;

public class Source {
    private final String value;
    private final Matcher matcher;

    public Source(String value,Matcher matcher) {
        this.value = value;
        this.matcher = matcher;
    }

    public String getValue() {
        return value;
    }

    public Source.SourceItem get(int index) {
        return new Source.SourceItem(this.matcher.group(index));
    }

    final static class SourceItem {
        private final String value;

        public SourceItem(String value) {
            this.value = value;
        }

        public String asChars() {
            return value;
        }
    }
}
