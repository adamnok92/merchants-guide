package org.adamnok.merchant.model.handlers;

import java.util.List;
import java.util.regex.Matcher;

public interface Source {
    SourceItem get(int index);

    static Source of(Matcher matcher) {
        return new SourceImpl(matcher);
    }

    static Source of(List<String> list) {
        return new SourceListImpl(list);
    }

    final class SourceItem {
        private final String value;

        public SourceItem(String value) {
            this.value = value;
        }

        public String asChars() {
            return value;
        }
    }
}
