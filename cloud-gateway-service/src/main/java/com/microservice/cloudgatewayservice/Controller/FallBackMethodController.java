package com.microservice.cloudgatewayservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallBackMethodController {

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBackMethod(){
        return "Product service currently unavailable";
    }
    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallBackMethod(){
        return "Order service currently unavailable";
    }
}
