package com.carrental.carrental.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Long> {

    @Query("SELECT COUNT(r) FROM Return r INNER JOIN Customer c ON c.customerId = ?1 WHERE c.customerId = ?1")
    long countByOwner(Long customerId);
}
