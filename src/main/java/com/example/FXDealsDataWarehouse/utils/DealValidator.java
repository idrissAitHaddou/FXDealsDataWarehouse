package com.example.FXDealsDataWarehouse.utils;

import com.example.FXDealsDataWarehouse.dto.DealRequest;
import com.example.FXDealsDataWarehouse.exceptions.InvalidDealException;
import org.springframework.stereotype.Component;

@Component
public class DealValidator {
    public void validate(DealRequest request) {
        if (request.getFromCurrencyIsoCode().equals(request.getToCurrencyIsoCode())) {
            throw new InvalidDealException("From and To currency cannot be the same");
        }
    }
}

