package com.carrental.carrental.web;

import com.carrental.carrental.domain.Car;
import com.carrental.carrental.domain.CarRepository;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @MockBean
    private CarRepository carRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getCars() throws Exception {

        when(carRepository.findAll()).thenReturn(prepareMockData());

        this.mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(view().name("cars/listOfCars"));
    }

    @Test
    void carForm() throws Exception {

        this.mockMvc.perform(get("/carForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("cars/carForm"));
    }

    @Test
    void carDetail() throws Exception {

        when(carRepository.findById(1L)).thenReturn(Optional.of(new Car("Ford",
                "BWM",
                "Blue",
                "AB-100",
                2000,
                5000)));

        this.mockMvc.perform(get("/cars/{carID}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("cars/car-detail"));
    }

    @Test
    void createCar() throws Exception {

        Car car = new Car();
        car.setCarId(1L);
        car.setBrand("Ford");
        car.setModel("BMW");
        car.setColor("Blue");
        car.setRegisterNumber("AB-100");
        car.setYear(2000);
        car.setPrice(5000);

        when(carRepository.save(any(Car.class))).thenReturn(car);

        this.mockMvc.perform(post("/add-car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand", is(car.getBrand())))
                .andExpect(jsonPath("$.model", is(car.getModel())))
                .andExpect(jsonPath("$.color", is(car.getColor())))
                .andExpect(jsonPath("$.registerNumber", is(car.getRegisterNumber())))
                .andExpect(jsonPath("$.year", is(car.getYear())))
                .andExpect(jsonPath("$.price", is(car.getPrice())));

    }


 /*   @Test
    void getCars(Model model) {
        //given
        CarController carController = mock(CarController.class);
        when(carController.getCars(model)).thenReturn(prepareMockData());
        //when
        //then

    }  */

    private List<Car> prepareMockData() {
         List<Car> cars = new ArrayList<>();

         cars.add(new Car("Ford", "BWM", "Blue", "AB-100", 2000, 5000));
         cars.add(new Car("Ford2", "BWM2", "Blue2", "AB-101", 2002, 5500));
         return cars;
    }
}