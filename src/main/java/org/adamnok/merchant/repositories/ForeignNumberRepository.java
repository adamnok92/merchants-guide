package org.adamnok.merchant.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class ForeignNumberRepository {
    public Set<String> getAllForeignNumbers(){
        throw new UnsupportedOperationException();
    }

    public void register(ForeignNumber number) {
        throw new UnsupportedOperationException();
    }

    public String getRomanNumber(List<String> foreignNumbers) {
        throw new UnsupportedOperationException();
    }
}
