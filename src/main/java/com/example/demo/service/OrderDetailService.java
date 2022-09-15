package com.example.demo.service;

import com.example.demo.model.OrderDetail;
import com.example.demo.model.PurchaseOrder;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IPurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailService implements IOrderDetailService{

    private final IOrderDetailRepository orderDetailRepository;
    private final IPurchaseOrderRepository purchaseOrderRepository;

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        log.info("Guardando detalle de orden {} en la base de datos",orderDetail.getId());
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public Optional<OrderDetail> getOrderDetail(Long id) {
        log.info("Buscando el detalle de orden {} en la base de datos",id);
        return orderDetailRepository.findById(id);
    }

    @Override
    public void addOrderDetailToOrder(Long orderDetailID, Long orderID) {
        Optional<PurchaseOrder> order = purchaseOrderRepository.findById(orderID);
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(orderDetailID);
        if(orderDetail.isPresent() && order.isPresent()) orderDetail.get().setPurchaseOrder(order.get());
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrder(Long orderID) {
        log.info("Buscando todas los detalles de ordenes de la orden de compra {} en la base de datos",orderID);
        return orderDetailRepository.findByPurchaseOrder(orderID);
    }
}
