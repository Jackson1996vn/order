package com.example.orderservice.domain.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemResponse {

    private Long id;

    private Long quantity;

    private Double price;
}
