package com.carrental.carrental.web;

import com.carrental.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.carrental.carrental.domain.Customer;


@RestController("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
       return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping
    public Iterable<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "/api/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping(path = "/api/customers/{id}")
    public Customer updateCustomer(@PathVariable("id") long customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customer, customerId);
    }

    @DeleteMapping(path = "/api/customers/{id}")
    public void deleteCustomer(@PathVariable("id") long customerId) {
        customerService.deleteCustomer(customerId);
    }

}
