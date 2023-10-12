package com.carrental.carrental.service;

public interface ReportService {

    String generateDailyReport();

    String generatePeriodicalReport();

    public double calculateTotalSales();
}
