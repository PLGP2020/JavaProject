package com.carrental.carrental.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


    List<Car> findByBrand(String brand);

    List<Car> findByColor(String color);

    List<Car> findByYear(int year);

    List<Car> findByBrandAndModel(String brand, String model);

    List<Car> findByBrandOrColor(String brand, String color);

    List<Car> findByBrandOrderByYearAsc(String brand);

    List<Car> findByBrandAndModelAndColorAndYearAndPrice(String brand, String model,
                                                         String color, int year, int price);

  //   @Query("SELECT c FROM Car c WHERE c.brand= ?1")
  //  List<Car> findByBrand(String brand);

    @Query("SELECT c FROM Car c WHERE c.brand like %?1")
    List<Car> findByBrandEndsWith(String brand);

    @Query("SELECT c FROM Car c WHERE c.model LIKE %?1%"
    + " OR c.brand LIKE %?1% "
    + " OR c.color LIKE %?1% "
    + " OR c.registerNumber LIKE %?1%"
    + " OR CONCAT(c.year, '') LIKE %?1%"
    + " OR CONCAT(c.price, '') LIKE %?1%")
    List<Car> search(String keyword);

    @Query("SELECT c FROM Car c WHERE c.year BETWEEN :startDate AND :endDate")
    List<Car> findByDateBetween(int startDate,int endDate);

    @Query("SELECT c FROM Car c WHERE c.price BETWEEN :minPrice AND :maxPrice")
    List<Car> findByPriceBetween(int minPrice, int maxPrice);
}