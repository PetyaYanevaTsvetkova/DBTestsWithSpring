package com.example.demo.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class CustomerSeedDTO {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private String email;
    @Expose
    private String phone;
    @Expose
    private Boolean consentStatus;
    @Expose
    private Boolean isProfileActive;
    @Expose
    private String dateProfileCreated;
    @Expose
    private String dateProfileDeactivated;
    @Expose
    private String reasonForDeactivation;
    @Expose
    private String notes;

    public CustomerSeedDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Min(1)
    @Max(100)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getConsentStatus() {
        return consentStatus;
    }

    public void setConsentStatus(Boolean consentStatus) {
        this.consentStatus = consentStatus;
    }

    public Boolean getProfileActive() {
        return isProfileActive;
    }

    public void setProfileActive(Boolean profileActive) {
        isProfileActive = profileActive;
    }

    public String getDateProfileCreated() {
        return dateProfileCreated;
    }

    public void setDateProfileCreated(String dateProfileCreated) {
        this.dateProfileCreated = dateProfileCreated;
    }

    public String getDateProfileDeactivated() {
        return dateProfileDeactivated;
    }

    public void setDateProfileDeactivated(String dateProfileDeactivated) {
        this.dateProfileDeactivated = dateProfileDeactivated;
    }

    public String getReasonForDeactivation() {
        return reasonForDeactivation;
    }

    public void setReasonForDeactivation(String reasonForDeactivation) {
        this.reasonForDeactivation = reasonForDeactivation;
    }

    @Size(max = 200)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
