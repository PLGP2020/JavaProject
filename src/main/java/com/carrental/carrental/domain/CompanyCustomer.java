package com.carrental.carrental.domain;

import com.carrental.carrental.embeddable.Address;
import jakarta.persistence.*;

@Entity
public class CompanyCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long companyCustomerId;

    @Column(name = "name")
    private String name;

    @Column(name = "NIP")
    private String NIP;

    @Column(name = "email")
    private String email;

    @Embedded
    private Address address;

    public long getCompanyCustomerId() {
        return companyCustomerId;
    }

    public void setCompanyCustomerId(long companyCustomerId) {
        this.companyCustomerId = companyCustomerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
