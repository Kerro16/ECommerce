package com.example.demo.service;

import com.example.demo.model.PurchaseOrder;

import java.util.List;
import java.util.Optional;

public interface IPurchaseOrderService {

    PurchaseOrder savePurchaseOrder(PurchaseOrder order);

    Optional<PurchaseOrder> getPurchaseOrder(Long id);

    List<PurchaseOrder> getPurchaseOrders();

    void addCustomerToPurchaseOrder(String customerName, String orderID);
}
