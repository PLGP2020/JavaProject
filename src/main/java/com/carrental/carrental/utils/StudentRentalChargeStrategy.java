package com.carrental.carrental.utils;

import com.carrental.carrental.domain.Rental;
import org.springframework.stereotype.Component;

@Component
public class StudentRentalChargeStrategy implements RentalChargeStrategy {

    @Override
    public String getName() {
        return "STUDENT";
    }

    @Override
    public double calculateCharge(Long customerId) {
        return 0;
    }
}
