package com.example.Project_Rishit.airBnbApp.Strategy;

import com.example.Project_Rishit.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class UrgencyPricingStrategy implements  PricingStrategy {
    private final PricingStrategy wrapped;

    @Override
    public BigDecimal CalculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.CalculatePrice(inventory);
        LocalDate today = LocalDate.now();
        if(inventory.getDate().isBefore(today.plusDays(7))){
            price=price.multiply(BigDecimal.valueOf(1.15));
        }
        return price;
    }
}
