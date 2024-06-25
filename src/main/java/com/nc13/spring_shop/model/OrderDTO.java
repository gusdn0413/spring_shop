package com.nc13.spring_shop.model;

import lombok.Data;

@Data
public class OrderDTO {
    private int id;
    private int itemId;
    private int memberCustomerId;
    private int memberSellerId;
}
