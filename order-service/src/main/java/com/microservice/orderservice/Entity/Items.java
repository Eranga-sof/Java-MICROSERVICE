package com.microservice.orderservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.microservice.orderservice.DTO.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="order_id")
    private Cart order;

    @Transient
    private Product product;

}
