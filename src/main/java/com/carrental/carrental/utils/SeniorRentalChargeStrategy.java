package com.carrental.carrental.utils;

import com.carrental.carrental.domain.Rental;
import org.springframework.stereotype.Component;

@Component
public class SeniorRentalChargeStrategy implements RentalChargeStrategy {

    @Override
    public String getName() {
        return "SENIOR";
    }

    @Override
    public double calculateCharge(Long customerId) {
        return 0;
    }
}
