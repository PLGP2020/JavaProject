package com.carrental.carrental.service;

import com.carrental.carrental.domain.Car;


import java.util.List;
import java.util.Optional;

public interface CarService {

     Iterable<Car> getAllCars();

     void saveCar(Car car);

     Optional<Car> getCarById(Long id);

     void deleteCarById(Long id);

     List<Car> search(String keyword);

     List<Car> findByDateBetween(int startDate,int endDate);

     List<Car> findByPriceBetween(int minPrice, int maxPrice);

     Car updateCar(Car car, Long id);

}
