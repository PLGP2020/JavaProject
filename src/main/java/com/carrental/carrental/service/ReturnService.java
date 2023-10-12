package com.carrental.carrental.service;

import com.carrental.carrental.domain.Invoice;
import com.carrental.carrental.domain.Rental;
import com.carrental.carrental.domain.Return;

public interface ReturnService {

    boolean rentDateExpired(Rental rental);

    long numOfDaysExpired(Rental rental);

    double calculateAdditionalChargeForDelay(Rental rental);

    double calculateAdditionalChargeForDamage(Return rentalReturn);

}
