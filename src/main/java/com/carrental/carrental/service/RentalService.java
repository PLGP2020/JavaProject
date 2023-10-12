package com.carrental.carrental.service;

import com.carrental.carrental.domain.Customer;
import com.carrental.carrental.domain.Rental;
import com.carrental.carrental.exceptions.CustomerBlockedException;
import com.carrental.carrental.exceptions.MaxRentedCarsExceededException;

public interface RentalService {

    long countByCustomerId(long customerId);

    Rental saveRental(Rental rental) throws MaxRentedCarsExceededException;

    void checkCustomer(Customer owner) throws CustomerBlockedException;
}
