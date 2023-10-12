package com.carrental.carrental.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT SUM(r.costs * r.numOfDays) FROM Rental r INNER JOIN Customer c ON c.customerId = :customerId")
    double calculateTotalRentalChargeForCustomer(@Param("customerId") Long customerId);
}
