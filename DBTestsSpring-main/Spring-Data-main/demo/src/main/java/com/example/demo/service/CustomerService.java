package com.example.demo.service;

import com.example.demo.model.entity.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void seedCustomersData() throws IOException;

    Customer findRandomCustomer();

    List<Customer> findAllCustomers();

    void saveCustomer() throws IOException;

    void saveCustomerWithoutMandatoryFields(Customer customer) throws IOException;

    Customer findCustomerById(Long id);

    Customer createCustomerWithoutMandatoryFields();

    Long getDBCount();

    List<Customer> findRandomCustomers();
}
