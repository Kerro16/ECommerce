package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customerID", nullable = false)
    private CustomerData customerData;

    private Integer ammount;

    private String shipping_address;

    private String order_address;

    private String order_email;

    private String order_date;

    private String order_status;
}
