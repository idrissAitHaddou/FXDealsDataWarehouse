package com.example.FXDealsDataWarehouse.controllers;

import com.example.FXDealsDataWarehouse.dto.DealRequest;
import com.example.FXDealsDataWarehouse.services.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @PostMapping
    public ResponseEntity<String> importDeal(@Valid @RequestBody DealRequest request) {
        dealService.importDeal(request);
        return ResponseEntity.ok("Deal imported successfully");
    }
}

