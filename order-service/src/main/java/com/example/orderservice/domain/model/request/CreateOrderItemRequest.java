package com.example.orderservice.domain.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderItemRequest {

    private Long productId;

    private Long quantity;

    private Double price;
}
