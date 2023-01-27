package org.adamnok.merchant.model.state;

import org.adamnok.merchant.model.RomanNumberService;
import org.adamnok.merchant.repositories.Change;
import org.adamnok.merchant.repositories.ChangeRepository;
import org.adamnok.merchant.repositories.ForeignNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StateService implements ReadonlyState {
    private final RomanNumberService romanNumberService;

    private final ChangeRepository changeRepository;

    private final ForeignNumberRepository foreignNumberRepository;

    @Autowired
    public StateService(RomanNumberService romanNumberService, ChangeRepository changeRepository, ForeignNumberRepository foreignNumberRepository) {
        this.romanNumberService = romanNumberService;
        this.changeRepository = changeRepository;
        this.foreignNumberRepository = foreignNumberRepository;
    }

    @Override
    public Set<String> getAllForeignNumbers() {
        return this.foreignNumberRepository.getAllForeignNumbers();
    }

    @Override
    public Set<String> getAllMaterialNames() {
        return this.changeRepository.getAllMaterialNames();
    }

    @Override
    public String getRomanNumber(String foreignNumbers) {
        return this.foreignNumberRepository.getRomanNumber(
            Arrays.stream(foreignNumbers.split(" "))
                .map(String::trim)
                .filter(it -> !it.isEmpty())
                .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<Integer> getNumber(String foreignNumbers) {
        return this.romanNumberService.fromRoman(getRomanNumber(foreignNumbers));
    }

    @Override
    public Optional<Change> getMaterialChange(String fromMaterialName, String toMaterialName) {
        return this.changeRepository.get(fromMaterialName, toMaterialName);
    }
}
