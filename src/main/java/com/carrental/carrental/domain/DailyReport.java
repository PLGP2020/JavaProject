package com.carrental.carrental.domain;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class DailyReport extends Report {

    private BigDecimal totalSales;

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
}
