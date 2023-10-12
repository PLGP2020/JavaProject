package com.carrental.carrental.utils;

import com.carrental.carrental.domain.Rental;

public interface RentalChargeStrategy {

    String getName();

    double calculateCharge(Long id);
}


