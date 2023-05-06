package com.microservice.orderservice.DTO;

import com.microservice.orderservice.Entity.Items;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartResponse {
    private Date createDate;
    private Date modifyDate;

    private List<Items> items;
}


