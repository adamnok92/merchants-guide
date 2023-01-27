package org.adamnok.merchant.model.state;

import org.adamnok.merchant.model.RomanNumberService;
import org.adamnok.merchant.repositories.Change;
import org.adamnok.merchant.repositories.ChangeRepository;
import org.adamnok.merchant.repositories.ForeignNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StateServiceTest {

    private ChangeRepository changeRepository;
    private ForeignNumberRepository foreignNumberRepository;
    private StateService state;

    @BeforeEach
    void setUp() {
        changeRepository = mock(ChangeRepository.class);
        foreignNumberRepository = mock(ForeignNumberRepository.class);
        state = new StateService(
            new RomanNumberService(),
            changeRepository,
            foreignNumberRepository
        );
    }

    @Test
    void getAllForeignNumbers() {
        when(foreignNumberRepository.getAllForeignNumbers()).thenReturn(Set.of("Alpha", "Beta"));
        final var result = state.getAllForeignNumbers();
        assertEquals(Set.of("Alpha", "Beta"), result);
    }

    @Test
    void getAllMaterialNames() {
        when(changeRepository.getAllMaterialNames()).thenReturn(Set.of("Alpha", "Beta"));
        final var result = state.getAllMaterialNames();
        assertEquals(Set.of("Alpha", "Beta"), result);
    }

    @Test
    void getRomanNumber() {
        when(foreignNumberRepository.getRomanNumber(List.of("A", "B", "C")))
            .thenReturn("IX");
        final var result = state.getRomanNumber("A B C");
        assertEquals("IX", result);
    }

    @Test
    void getNumber() {
        when(foreignNumberRepository.getRomanNumber(List.of("A", "B", "C")))
            .thenReturn("IX");
        final var result = state.getNumber("A B C");
        assertEquals(Optional.of(9), result);
    }

    @Test
    void getMaterialChange() {
        final var change = new Change(
            "Silver",
            "Credits",
            6,
            11
        );
        when(changeRepository.get("A", "B"))
            .thenReturn(Optional.of(change));
        final var result = state.getMaterialChange("A", "B");
        assertEquals(Optional.of(change), result);
    }
}