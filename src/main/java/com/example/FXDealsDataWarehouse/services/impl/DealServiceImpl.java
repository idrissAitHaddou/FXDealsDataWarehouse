package com.example.FXDealsDataWarehouse.services.impl;

import com.example.FXDealsDataWarehouse.dto.DealRequest;
import com.example.FXDealsDataWarehouse.entities.Deal;
import com.example.FXDealsDataWarehouse.exceptions.DealAlreadyExistsException;
import com.example.FXDealsDataWarehouse.repositories.DealRepository;
import com.example.FXDealsDataWarehouse.services.DealService;
import com.example.FXDealsDataWarehouse.utils.DealValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository repository;
    private final DealValidator validator;

    @Override
    public void importDeal(DealRequest request) {
        validator.validate(request);

        repository.findByDealUniqueId(request.getDealUniqueId())
                .ifPresent(d -> {
                    throw new DealAlreadyExistsException("Deal already exists: " + d.getDealUniqueId());
                });

        Deal deal = Deal.builder()
                .dealUniqueId(request.getDealUniqueId())
                .fromCurrencyIsoCode(request.getFromCurrencyIsoCode())
                .toCurrencyIsoCode(request.getToCurrencyIsoCode())
                .dealTimestamp(LocalDateTime.parse(request.getDealTimestamp(), DateTimeFormatter.ISO_DATE_TIME))
                .dealAmount(request.getDealAmount())
                .build();
        repository.save(deal);
    }
}

