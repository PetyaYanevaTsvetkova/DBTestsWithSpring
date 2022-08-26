package com.example.demo.service;

import com.example.demo.model.entity.Address;
import com.example.demo.model.entity.Customer;

import java.io.IOException;
import java.util.List;

public interface AddressService {
    void seedAddressData() throws IOException;

    Address findRandomAddress();

    Address findAddressById(Long id);

    List<Address> findAllAddresses();

    Address createAddressWithoutMandatoryFields();

    void saveAddress(Address address);
}
