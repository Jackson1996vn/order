package com.example.orderservice.domain.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {

    private Long id;

    private Long userId;

    private Double totalPrice;

    private String orderStatus;

    private List<OrderItemResponse> items;
}
