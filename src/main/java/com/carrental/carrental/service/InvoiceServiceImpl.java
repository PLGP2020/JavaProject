package com.carrental.carrental.service;

import com.carrental.carrental.domain.Customer;
import com.carrental.carrental.domain.Invoice;
import com.carrental.carrental.domain.InvoiceRepository;
import com.carrental.carrental.enums.CustomerStatus;
import com.carrental.carrental.utils.RentalChargeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private List<RentalChargeStrategy> chargeStrategies;

    private InvoiceRepository invoiceRepository;

    @Autowired
    private RentalService rentalService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, List<RentalChargeStrategy> chargeStrategies) {
        super();
        this.invoiceRepository = invoiceRepository;
        this.chargeStrategies = chargeStrategies;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).get();
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public double calculateRentalCharge(Customer customer) {
        return 0;
    }

/*    @Override
    public double calculateRentalCharge(Customer customer) {

        CustomerStatus customerStatus = customer.getCustomerStatus();

        RentalChargeStrategy chargeStrategy = findChargeStrategy(CustomerStatus.valueOf(customerStatus));

        double baseCharge = chargeStrategy.calculateCharge(customer.getCustomerId());

        return baseCharge;
    } */

    private RentalChargeStrategy findChargeStrategy(String strategyName) {

        return chargeStrategies.stream()
                .filter(chargeStrategy -> chargeStrategy.getName().equals(strategyName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Find no repository for given repository name \\[%s\\]", strategyName)));
    }
}
