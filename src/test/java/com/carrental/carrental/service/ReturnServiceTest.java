package com.carrental.carrental.service;

import com.carrental.carrental.domain.Rental;
import com.carrental.carrental.domain.Return;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ReturnServiceTest {

    @InjectMocks
    private ReturnService returnService;

    @Test
    public void testAdditionalChargeForDelay() {
        // Given

        Return rentalReturn = new Return();
        Rental rental = new Rental();
        rentalReturn.setDelayDays(3);

        double expectedCharge = 30.0;

        // When

        double additionalCharge = returnService.calculateAdditionalChargeForDelay(rental);

        // Then

        assertEquals(expectedCharge, additionalCharge, 0.0001);
    }

    @Test
    public void testAdditionalChargeForDamage() {
        // Given

        Return rentalReturn = new Return();
        rentalReturn.setDamageAmount(2);
        rentalReturn.setDamageRate(50);

        double expectedCharge = 100;

        // When

        double additionalCharge = returnService.calculateAdditionalChargeForDamage(rentalReturn);

        // Then

        assertEquals(expectedCharge, additionalCharge);
    }
}
