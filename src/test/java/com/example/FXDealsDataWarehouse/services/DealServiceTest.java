package com.example.FXDealsDataWarehouse.services;

import com.example.FXDealsDataWarehouse.dto.DealRequest;
import com.example.FXDealsDataWarehouse.entities.Deal;
import com.example.FXDealsDataWarehouse.exceptions.DealAlreadyExistsException;
import com.example.FXDealsDataWarehouse.repositories.DealRepository;
import com.example.FXDealsDataWarehouse.services.impl.DealServiceImpl;
import com.example.FXDealsDataWarehouse.utils.DealValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DealServiceTest {

    @Mock
    private DealRepository repository;

    @Mock
    private DealValidator validator;

    @InjectMocks
    private DealServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private DealRequest buildRequest() {
        DealRequest r = new DealRequest();
        r.setDealUniqueId("123");
        r.setFromCurrencyIsoCode("USD");
        r.setToCurrencyIsoCode("EUR");
        r.setDealTimestamp(LocalDateTime.now().toString());
        r.setDealAmount(1000.0);
        return r;
    }

    @Test
    void shouldSaveDealSuccessfully() {
        DealRequest request = buildRequest();

        when(repository.findByDealUniqueId("123")).thenReturn(Optional.empty());
        when(repository.save(any(Deal.class))).thenAnswer(i -> i.getArguments()[0]);

        assertDoesNotThrow(() -> service.importDeal(request));
        verify(repository, times(1)).save(any(Deal.class));
    }

    @Test
    void shouldThrowWhenDealAlreadyExists() {
        DealRequest request = buildRequest();
        when(repository.findByDealUniqueId("123")).thenReturn(Optional.of(new Deal()));

        assertThrows(DealAlreadyExistsException.class, () -> service.importDeal(request));
        verify(repository, never()).save(any());
    }
}

