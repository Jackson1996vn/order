package com.example.orderservice.domain.port.outgoing;

import com.example.orderservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderOutgoing {

    Order createOrder(Order order);

    List<Order> findOrdersByUserId(Long userId);

    Optional<Order> findById(Long id);
}
