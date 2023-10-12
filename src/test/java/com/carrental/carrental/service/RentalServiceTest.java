package com.carrental.carrental.service;

import com.carrental.carrental.domain.Customer;
import com.carrental.carrental.domain.Rental;
import com.carrental.carrental.domain.RentalRepository;
import com.carrental.carrental.enums.RentalStatus;
import com.carrental.carrental.exceptions.MaxRentedCarsExceededException;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalServiceImpl rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCountByCustomerId() {

        // Given

        long customerId = 1;
        long expectedCount = 5;

        Mockito.when(rentalRepository.countByCustomer(customerId, RentalStatus.RENTED)).thenReturn(expectedCount);

        // When

        long count = rentalService.countByCustomerId(customerId);

        // Then

        assertEquals(expectedCount, count);
    }

    @Test
    public void testSaveRentalWithValidData() throws MaxRentedCarsExceededException {

        // Given

        Rental rental = new Rental();
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Johnson");
        customer.setEmail("john.johnson@example.com");
        customer.setPhoneNumber("679000122");
        customer.setDelayCount(0);
        customer.setBlocked(false);
        rental.setCustomer(customer);
        long customerId = rental.getCustomer().getCustomerId();
        Mockito.when(rentalRepository.countByCustomer(customerId, RentalStatus.RENTED)).thenReturn(4L);
        Mockito.when(rentalRepository.save(rental)).thenReturn(rental);

        // When

        Rental savedRental = rentalService.saveRental(rental);

        // Then

        Mockito.verify(rentalRepository, Mockito.times(1)).save(rental);
        Mockito.verify(rentalRepository, Mockito.times(1)).countByCustomer(customerId, RentalStatus.RENTED);
        assertEquals(rental, savedRental);

    }


    @Test
    public void testRentalWithExceededLimit() {

        Rental rental = new Rental();
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Johnson");
        customer.setEmail("john.johnson@example.com");
        customer.setPhoneNumber("679000122");
        customer.setDelayCount(0);
        customer.setBlocked(false);
        rental.setCustomer(customer);
        long customerId = rental.getCustomer().getCustomerId();
        rental.setCustomer(customer);

        Mockito.when(rentalRepository.countByCustomer(customerId, RentalStatus.RENTED)).thenReturn(6L);

        Assertions.assertThrows(MaxRentedCarsExceededException.class,
                () -> {rentalService.saveRental(rental);});

      //  Mockito.verify(rentalRepository, Mockito.times(1)).save(rental);
        Mockito.verify(rentalRepository, Mockito.times(1)).countByCustomer(customerId, RentalStatus.RENTED);
        Mockito.verify(rentalRepository, Mockito.never()).save(any(Rental.class));

    }

}
