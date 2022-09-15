package com.example.demo.service;

import com.example.demo.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService {

    OrderDetail saveOrderDetail(OrderDetail orderDetail);

    Optional<OrderDetail> getOrderDetail(Long id);

    void addOrderDetailToOrder(Long orderDetailID, Long OrderID);

    List<OrderDetail> getOrderDetailByOrder(Long orderID);

}
