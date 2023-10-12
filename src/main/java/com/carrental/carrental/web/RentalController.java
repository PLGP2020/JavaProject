package com.carrental.carrental.web;

import com.carrental.carrental.domain.CarRepository;
import com.carrental.carrental.domain.Rental;
import com.carrental.carrental.domain.RentalRepository;
import com.carrental.carrental.exceptions.MaxRentedCarsExceededException;
import com.carrental.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private CarRepository carRepository;



    @GetMapping(path = "/rentals")
    public String getRentals(Model model) {
        model.addAttribute("rentals", rentalRepository.findAll());
       // model.addAttribute("car", carRepository.findById();
        return "rentals/list_of_rentals";
    }

    @GetMapping("/rentals/{rentalID}")
    public String rentalDetails(Model model, @PathVariable("rentalID") Long id) {
        model.addAttribute("rental", rentalRepository.findById(id));
        return "rentals/rental_details";
    }

    @GetMapping("/rentalForm")
    public String rentalForm(Model model) {
        Rental rental = new Rental();
        model.addAttribute("rental", rental);
        return "rentals/rental_form";
    }

    @PostMapping("/add_rental")
    public String addRental(@ModelAttribute("rental") Rental rental, Model model) throws MaxRentedCarsExceededException {

        rentalService.saveRental(rental);

        return "redirect:/rentals";
    }

}
