package com.carrental.carrental.domain;

import com.carrental.carrental.enums.RentalStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentalID;

    private LocalDate dateOfOrder;

    private double costs;

    private int numOfDays;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    private Customer customer;

  //   @ManyToOne(fetch = FetchType.LAZY)
  //   @JoinColumn(name = "employee")
  //  private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice")
    private Invoice invoice;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //  public Employee getEmployee() {
    //    return employee;
  //  }

  //  public void setEmployee(Employee employee) {
  //      this.employee = employee;
  //  }

    public long getRentalID() {
        return rentalID;
    }

    public void setRentalID(long rentalID) {
        this.rentalID = rentalID;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(LocalDate dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public Rental() {
    }

    public Rental(LocalDate dateOfOrder, double costs, int numOfDays,
                  Car car, Customer customer, RentalStatus rentalStatus) {
        this.dateOfOrder = dateOfOrder;
        this.costs = costs;
        this.numOfDays = numOfDays;
        this.car = car;
        this.customer = customer;
        this.rentalStatus = rentalStatus;
    //    this.employee = employee;
    }
}
