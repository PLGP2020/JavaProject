package com.carrental.carrental.service;

import com.carrental.carrental.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomerById(Long id);

    Customer updateCustomer(Customer customer, Long id);

    void deleteCustomer(Long id);

}
