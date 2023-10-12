package com.carrental.carrental.repository;

import com.carrental.carrental.domain.Invoice;
import com.carrental.carrental.domain.InvoiceRepository;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveInvoiceTest() {
        Invoice invoice = Invoice.builder()
                .dateOfIssue(LocalDate.of(2023,05, 10))
                .dateOfPayment(LocalDate.of(2023, 05, 12))
                .paymentMethod("Card")
                .totalAmount(300.0)
                .build();

        invoiceRepository.save(invoice);

        assertThat(invoice.getInvoiceID()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getInvoiceTest() {

        Invoice invoice = invoiceRepository.findById(1L).get();

        assertThat(invoice.getInvoiceID()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void getListOfInvoicesTest() {

        List<Invoice> invoices = invoiceRepository.findAll();

        assertThat(invoices.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateInvoiceTest() {
        Invoice invoice = invoiceRepository.findById(1L).get();

        invoice.setTotalAmount(500.0);
        Invoice invoiceUpdated = invoiceRepository.save(invoice);

        assertThat(invoiceUpdated.getTotalAmount()).isEqualTo(500.0);
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteInvoiceTest() {

        Invoice invoice = invoiceRepository.findById(1L).get();

        invoiceRepository.delete(invoice);

        Invoice invoice1 = null;

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(1L);

        if(optionalInvoice.isPresent()) {
            invoice1  = optionalInvoice.get();
        }

        assertThat(invoice1).isNull();

    }
}
