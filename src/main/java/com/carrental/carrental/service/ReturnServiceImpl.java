package com.carrental.carrental.service;

import com.carrental.carrental.domain.Invoice;
import com.carrental.carrental.domain.Customer;
import com.carrental.carrental.domain.Rental;
import com.carrental.carrental.domain.Return;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Override
    public boolean rentDateExpired(Rental rental) {

        Customer customer = rental.getCustomer();
        customer.setDelayCount(customer.getDelayCount() + 1);

        LocalDate today = LocalDate.now();
        LocalDate returnDate = rental.getDateOfOrder().plusDays(rental.getNumOfDays());

        return today.isAfter(returnDate);
    }

    @Override
    public long numOfDaysExpired(Rental rental) {

        LocalDate today = LocalDate.now();
        LocalDate returnDate = rental.getDateOfOrder().plusDays(rental.getNumOfDays());
        Duration diff = Duration.between(today.atStartOfDay(), returnDate.atStartOfDay());
        long numOfDays = diff.toDays();

        return numOfDays;
    }

    @Override
    public double calculateAdditionalChargeForDelay(Rental rental) {

        if(rentDateExpired(rental)) {
            double delayCharge = numOfDaysExpired(rental) * rental.getCosts() * 0.1;
            return delayCharge;
        }

        return 0;
    }

    @Override
    public double calculateAdditionalChargeForDamage(Return rentalReturn) {

        double damageCharge = rentalReturn.getDamageAmount() * rentalReturn.getDamageRate();

        return damageCharge;
    }
}
























