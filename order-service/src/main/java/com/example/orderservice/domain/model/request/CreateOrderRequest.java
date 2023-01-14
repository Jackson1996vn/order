package com.example.orderservice.domain.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {

    @ApiModelProperty(notes = "UserId")
    private Long userId;

    @NotEmpty
    private List<CreateOrderItemRequest> items;
}
