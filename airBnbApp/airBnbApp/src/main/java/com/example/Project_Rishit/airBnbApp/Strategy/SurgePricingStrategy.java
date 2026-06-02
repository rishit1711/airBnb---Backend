package com.example.Project_Rishit.airBnbApp.Strategy;

import com.example.Project_Rishit.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class SurgePricingStrategy implements PricingStrategy{
    private final PricingStrategy wrapped;
    @Override
    public BigDecimal CalculatePrice(Inventory inventory) {
        return wrapped.CalculatePrice(inventory).multiply(inventory.getSurgeFactor());
    }
}
