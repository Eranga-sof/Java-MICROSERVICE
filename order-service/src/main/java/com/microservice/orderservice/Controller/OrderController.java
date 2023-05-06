package com.microservice.orderservice.Controller;

import com.microservice.orderservice.DTO.CartResponse;
import com.microservice.orderservice.Entity.Cart;
import com.microservice.orderservice.Service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    private Cart addOrder(@RequestBody Cart order){
       return orderService.addOrder(order);
    }

    @GetMapping("")
    public List<Cart> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public CartResponse getCartById(@PathVariable Long id){
        return orderService.getCartById(id);
    }
}
