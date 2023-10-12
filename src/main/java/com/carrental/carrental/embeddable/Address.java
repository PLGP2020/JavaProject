package com.carrental.carrental.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String city;
    private String zipCode;
    private int homeNumber;

}
