package com.example.demo.service;

import com.example.demo.model.CustomerData;
import com.example.demo.repository.ICustomerDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerDataService implements ICustomerDataService {

    private final ICustomerDataRepository customerRepository;

    @Override
    public CustomerData saveCustomer(CustomerData customerData) {
        log.info("Guardando un nuevo cliente {} en la base de datos", customerData.getUser().getUsername());
        return customerRepository.save(customerData);
    }

    @Override
    public Optional<CustomerData> getCustomer(String userID) {
        log.info("Buscando cliente {} en la base de datos", userID);
        return Optional.ofNullable(customerRepository.findByUser(userID));
    }

    @Override
    public List<CustomerData> getCustomers() {
        log.info("Buscando todos los clientes de la base de datos");
        return customerRepository.findAll();
    }
}
