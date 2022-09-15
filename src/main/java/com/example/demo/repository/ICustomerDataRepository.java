package com.example.demo.repository;

import com.example.demo.model.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerDataRepository extends JpaRepository<CustomerData,Long> {
    CustomerData findByUser(String userID);
}
