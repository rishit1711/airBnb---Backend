package com.example.Project_Rishit.airBnbApp.Strategy;

import com.example.Project_Rishit.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy{
    private final PricingStrategy wrapped;
    @Override
    public BigDecimal CalculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.CalculatePrice(inventory);
        boolean isHolidayToday =true; //TODO: Calling an API with local date
        if(isHolidayToday){
            price=price.multiply(BigDecimal.valueOf(1.5));
        }
        return  price;

    }
}
