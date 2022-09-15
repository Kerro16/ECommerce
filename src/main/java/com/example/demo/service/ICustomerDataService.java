package com.example.demo.service;

import com.example.demo.model.CustomerData;

import java.util.List;
import java.util.Optional;

public interface ICustomerDataService {

    CustomerData saveCustomer(CustomerData customerData);

    Optional<CustomerData> getCustomer(String email);

    List<CustomerData> getCustomers();
}
