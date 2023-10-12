package com.carrental.carrental.repository;

import com.carrental.carrental.domain.Car;
import com.carrental.carrental.domain.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void saveCarTest() {


        Car car1 = new Car("Nissna", "Skyline", "White", "AA-100", 2000, 4000);

        carRepository.save(car1);

        Assertions.assertThat(car1.getCarId()).isGreaterThan(0);

    }

    @Test
    public void getCarTest() {

        Car car = carRepository.findById(1L).get();

        Assertions.assertThat(car.getCarId()).isEqualTo(1L);
    }

    @Test
    public void getListOfCars() {

        List<Car> cars = carRepository.findAll();

        Assertions.assertThat(cars.size()).isGreaterThan(0);

    }

    @Test
    public void updateCarTest() {

        Car car = carRepository.findById(1L).get();

        car.setColor("Black");

        Car updatedCar = carRepository.save(car);

        Assertions.assertThat(updatedCar.getColor()).isEqualTo("Black");
    }

    @Test
    public void deleteCarTest() {

        Car car = carRepository.findById(1L).get();

        carRepository.delete(car);

        Car car1 = null;

        Optional<Car> optionalCar = carRepository.findById(1L);

        if (optionalCar.isPresent()) {
            car1 = optionalCar.get();
        }

        Assertions.assertThat(car1).isNull();



    }
}
