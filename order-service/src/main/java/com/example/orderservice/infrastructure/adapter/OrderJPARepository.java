package com.example.orderservice.infrastructure.adapter;

import com.example.orderservice.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJPARepository extends JpaRepository<Order, Long> {

    @Query("SELECT ord FROM Order ord WHERE ord.userId = :userId")
    List<Order> findOrdersByUserId(@Param("userId") Long userId);
}
