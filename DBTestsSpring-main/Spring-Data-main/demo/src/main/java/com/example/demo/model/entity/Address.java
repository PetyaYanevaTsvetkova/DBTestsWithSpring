package com.example.demo.model.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {
    private Integer postalCode;
    private String address;
    private String city;
    private String country;
    private Set<Customer> customers;

    public Address() {
    }

    @Column(name = "postal_code")
    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "address")
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
