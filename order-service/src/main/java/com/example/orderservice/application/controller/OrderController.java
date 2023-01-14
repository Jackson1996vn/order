package com.example.orderservice.application.controller;

import com.example.orderservice.domain.model.Order;
import com.example.orderservice.domain.model.request.CreateOrderRequest;
import com.example.orderservice.domain.model.response.OrderResponse;
import com.example.orderservice.domain.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid
                                                     CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @Operation(summary = "Get list Orders by userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Orders",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User doesn't have order",
                    content = @Content)})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/userId/{id}}")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findOrdersByUserId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderId(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findOrderById(id));
    }
}
