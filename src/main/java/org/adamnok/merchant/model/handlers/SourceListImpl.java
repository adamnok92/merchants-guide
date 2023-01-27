package org.adamnok.merchant.model.handlers;

import java.util.List;

final class SourceListImpl implements Source {
    private final List<String> list;

    public SourceListImpl(List<String> list) {
        this.list = list;
    }

    public SourceItem get(int index) {
        return new SourceItem(this.list.get(index));
    }

}
