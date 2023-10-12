package com.carrental.carrental.service;

import com.carrental.carrental.domain.Customer;
import com.carrental.carrental.domain.Invoice;
import com.carrental.carrental.domain.Rental;

import java.util.List;

public interface InvoiceService {

  List<Invoice> getAllInvoices();

  Invoice saveInvoice(Invoice invoice);

  Invoice getInvoiceById(Long id);

  Invoice updateInvoice(Invoice invoice);

  void deleteInvoiceById(Long id);

  double calculateRentalCharge(Customer customer);

}
