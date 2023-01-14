package com.example.orderservice.infrastructure.adapter;

import com.example.orderservice.domain.model.Order;
import com.example.orderservice.domain.port.outgoing.OrderOutgoing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepository implements OrderOutgoing {

    private final OrderJPARepository jpaRepository;

    @Override
    public Order createOrder(Order order) {
        return jpaRepository.save(order);
    }

    @Override
    public List<Order> findOrdersByUserId(Long userId) {
        return jpaRepository.findOrdersByUserId(userId);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
