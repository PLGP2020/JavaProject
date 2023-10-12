package com.carrental.carrental.domain;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class PeriodicalReport extends Report {

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal avgSales;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getAvgSales() {
        return avgSales;
    }

    public void setAvgSales(BigDecimal avgSales) {
        this.avgSales = avgSales;
    }
}
