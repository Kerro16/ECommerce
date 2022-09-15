package com.example.demo.service;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.repository.ICustomerDataRepository;
import com.example.demo.repository.IPurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseOrderService implements IPurchaseOrderService {

    private final IPurchaseOrderRepository purchaseOrderRepository;
    private final ICustomerDataRepository customerRepository;

    @Override
    public PurchaseOrder savePurchaseOrder(PurchaseOrder order) {
        log.info("Guardando la nueva orden de compra {} en la base de datos", order.getId());
        return purchaseOrderRepository.save(order);
    }

    @Override
    public Optional<PurchaseOrder> getPurchaseOrder(Long id) {
        log.info("Buscando la orden de compra {} en la base de datos", id);
        return purchaseOrderRepository.findById(id);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrders() {
        log.info("Buscando todas las ordenes de compra");
        return null;
    }

    @Override
    public void addCustomerToPurchaseOrder(String customerName, String orderID) {
        log.info("Asignando cliente {} a la orden de compra {}", customerName, orderID);

    }
}
