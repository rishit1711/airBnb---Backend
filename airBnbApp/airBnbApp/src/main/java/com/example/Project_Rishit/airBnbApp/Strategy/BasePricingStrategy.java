package com.example.Project_Rishit.airBnbApp.Strategy;

import com.example.Project_Rishit.airBnbApp.entity.Inventory;

import java.math.BigDecimal;

public class BasePricingStrategy implements PricingStrategy{
    @Override
    public BigDecimal CalculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
