package com.microservice.orderservice.Repository;

import com.microservice.orderservice.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Cart, Long> {
}
