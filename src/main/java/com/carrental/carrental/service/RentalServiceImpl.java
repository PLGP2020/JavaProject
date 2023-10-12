package com.carrental.carrental.service;

import com.carrental.carrental.domain.Customer;
import com.carrental.carrental.domain.Rental;
import com.carrental.carrental.domain.RentalRepository;
import com.carrental.carrental.domain.ReturnRepository;
import com.carrental.carrental.enums.RentalStatus;
import com.carrental.carrental.exceptions.CustomerBlockedException;
import com.carrental.carrental.exceptions.MaxRentedCarsExceededException;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ReturnRepository returnRepository;



    @Override
    public long countByCustomerId(long customerId) {

        return rentalRepository.countByCustomer(customerId, RentalStatus.RENTED);
    }

    @Override
    public Rental saveRental(Rental rental) throws MaxRentedCarsExceededException {

        long customer_id = rental.getCustomer().getCustomerId();

        long rentedCarsCount = rentalRepository.countByCustomer(customer_id, RentalStatus.RENTED);

        if(rentedCarsCount < 5) {
            return rentalRepository.save(rental);
        } else {
            throw new MaxRentedCarsExceededException("Limit wypożyczonych samochodów przekroczony");
        }
    }

    @Override
    public void checkCustomer(Customer customer) throws CustomerBlockedException {

        if(customer.isBlocked()) {
            throw new CustomerBlockedException("Klient zablokowany na 2 lata");
        }


    }
}
