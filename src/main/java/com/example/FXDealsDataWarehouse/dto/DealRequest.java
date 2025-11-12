package com.example.FXDealsDataWarehouse.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DealRequest {
    @NotNull(message = "Deal id must not be null")
    @NotEmpty(message = "Deal id must not be empty")
    private String dealUniqueId;

    @NotNull(message = "Ordering currency iso code must not be null")
    @NotEmpty(message = "Ordering currency iso code must not be empty")
    private String fromCurrencyIsoCode;

    @NotNull(message = "To currency iso code must not be null")
    @NotEmpty(message = "To currency iso code must not be empty")
    private String toCurrencyIsoCode;

    @NotNull
    private String dealTimestamp;

    @NotNull(message = "Deal amount must not be null")
    @Max(value = (long) Double.MAX_VALUE, message = "to long value")
    @Min(value = 0, message = "deal amount should be a positive value.")
    private Double dealAmount;
}

