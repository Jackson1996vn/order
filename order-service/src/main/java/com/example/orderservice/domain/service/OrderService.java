package com.example.orderservice.domain.service;

import com.example.orderservice.domain.model.Order;
import com.example.orderservice.domain.model.OrderItem;
import com.example.orderservice.domain.model.OrderStatus;
import com.example.orderservice.domain.model.request.CreateOrderRequest;
import com.example.orderservice.domain.model.response.OrderItemResponse;
import com.example.orderservice.domain.model.response.OrderResponse;
import com.example.orderservice.domain.port.imcoming.OrderIncoming;
import com.example.orderservice.infrastructure.adapter.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderIncoming {

    private final OrderRepository repository;

    @Override
    public List<OrderResponse> findOrdersByUserId(Long userId) {
        return repository.findOrdersByUserId(userId).stream()
                .map(this::mappingToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        return mappingToOrderResponse(repository.createOrder(mappingToOrder(request)));
    }

    @Override
    public OrderResponse findOrderById(Long id) {
        Optional<Order> optionalOrder = repository.findById(id);
        return optionalOrder.map(this::mappingToOrderResponse).orElse(null);
    }

    private OrderResponse mappingToOrderResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(item -> OrderItemResponse.builder()
                        .id(item.getId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build()).collect(Collectors.toList());
        Double totalPrice = items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .totalPrice(totalPrice)
                .orderStatus(order.getStatus().name())
                .items(items)
                .build();
    }

    private Order mappingToOrder(CreateOrderRequest request) {
        return Order.builder()
                .userId(request.getUserId())
                .status(OrderStatus.ORDER_CREATED)
                .items(request.getItems().stream().map(item -> OrderItem.builder()
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .productId(item.getProductId())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}
