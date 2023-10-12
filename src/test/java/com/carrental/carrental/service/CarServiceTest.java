package com.carrental.carrental.service;


import com.carrental.carrental.domain.Car;
import com.carrental.carrental.domain.CarRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Test
    @DisplayName("Should save the car object to the database")
    void save() {

        Car car = new Car();
        car.setCarId(1L);
        car.setModel("Nissan");
        car.setBrand("Skyline");
        car.setColor("White");
        car.setRegisterNumber("AA-100");
        car.setPrice(4000);
        car.setYear(2000);
    }
}
