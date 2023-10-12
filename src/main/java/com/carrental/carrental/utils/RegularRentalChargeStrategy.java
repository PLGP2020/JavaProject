package com.carrental.carrental.utils;

import com.carrental.carrental.domain.InvoiceRepository;
import com.carrental.carrental.domain.Rental;
import org.springframework.stereotype.Component;

@Component
public class RegularRentalChargeStrategy implements RentalChargeStrategy {

    private InvoiceRepository invoiceRepository;

    @Override
    public String getName() {
        return "REGULAR";
    }

    @Override
    public double calculateCharge(Long customerId) {
        return invoiceRepository.calculateTotalRentalChargeForCustomer(customerId);
    }
}
