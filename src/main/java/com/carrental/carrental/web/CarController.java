package com.carrental.carrental.web;


import com.carrental.carrental.dao.CarSearchForm;
import com.carrental.carrental.domain.Car;
import com.carrental.carrental.domain.CarRepository;
import com.carrental.carrental.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarServiceImpl carService;

    public CarController(CarRepository carRepository, CarServiceImpl carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }


    // --------------REST-------------------------

    @PostMapping(path = "/api/cars/new")
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        return new ResponseEntity<Car>(carService.saveCar2(car), HttpStatus.CREATED);
    }

    @GetMapping(path = "/api/cars")
    public Iterable<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(path = "/api/cars/{id}")
    public Optional<Car> getCarById(@PathVariable("id") long carId) {
        return carService.getCarById(carId);
    }

    @PutMapping(path = "/api/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") long carId, @RequestBody Car car) {

        return new ResponseEntity<Car>(carService.updateCar(car, carId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/api/cars/{id}")
    public void deleteCar(@PathVariable("id") long carId) {
        carService.deleteCarById(carId);
    }


  /*

    @GetMapping("/cars/{carID}")
    public String carDetail(Model model, @PathVariable("carID") Long carID) {
        model.addAttribute("car", carRepository.findById(carID));
        return "cars/car-detail";
    }


    @GetMapping("/search_form")
    public String searchForm(Model model) {
        model.addAttribute("searchForm", new CarSearchForm());

        return "cars/search_car_form";
    }

    @GetMapping("/search_result")
    public String search(@ModelAttribute("searchForm") CarSearchForm carSearchForm, Model model) {

        int startDate = carSearchForm.getStartYear();
        int endDate = carSearchForm.getEndYear();
        List<Car> cars = carService.findByDateBetween(startDate, endDate);
        model.addAttribute("cars", cars);

        return "cars/car_search_result";
    }

   */

}
