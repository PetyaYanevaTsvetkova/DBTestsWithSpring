package com.example.demo.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Positive;

public class AddressSeedDTO {
    @Expose
    private Integer postalCode;
    @Expose
    private String address;
    @Expose
    private String city;
    @Expose
    private String country;

    public AddressSeedDTO() {
    }

    @Positive
    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
