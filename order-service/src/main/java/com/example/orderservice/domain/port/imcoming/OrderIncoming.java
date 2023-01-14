package com.example.orderservice.domain.port.imcoming;

import com.example.orderservice.domain.model.request.CreateOrderRequest;
import com.example.orderservice.domain.model.response.OrderResponse;

import java.util.List;

public interface OrderIncoming {

    List<OrderResponse> findOrdersByUserId(Long userId);

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse findOrderById(Long id);
}
