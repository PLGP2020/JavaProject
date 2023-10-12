package com.carrental.carrental.service;

import com.carrental.carrental.domain.Car;
import com.carrental.carrental.domain.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void saveCar(Car car) {
         carRepository.save(car);
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> search(String keyword) {
            if (keyword != null) {
                return carRepository.search(keyword);
            }
            return carRepository.findAll();
    }

    public Car saveCar2(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findByDateBetween(int startDate, int endDate) {

        return carRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<Car> findByPriceBetween(int minPrice, int maxPrice) {

        return carRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public Car updateCar(Car car, Long id) {

        Car existingCar = carRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException());

        existingCar.setModel(car.getModel());
        existingCar.setBrand(car.getBrand());
        existingCar.setColor(car.getColor());
        existingCar.setRegisterNumber(car.getRegisterNumber());
        existingCar.setYear(car.getYear());
        existingCar.setPrice(car.getPrice());

        carRepository.save(existingCar);

        return existingCar;

    }

}
