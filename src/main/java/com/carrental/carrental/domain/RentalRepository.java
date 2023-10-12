package com.carrental.carrental.domain;

import com.carrental.carrental.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("SELECT COUNT(r) FROM Rental r INNER JOIN Customer c ON c.customerId = :customerId " +
            "WHERE c.customerId = :customerId AND r.rentalStatus = :status")
    long countByCustomer(@Param("customerId") long customerId, @Param("status")RentalStatus status);

}
