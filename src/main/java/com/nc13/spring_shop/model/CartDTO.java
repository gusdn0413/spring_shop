package com.nc13.spring_shop.model;

import lombok.Data;

import java.util.Date;

@Data
public class CartDTO {
    private int id;
    private int itemId;
    private int memberCustomerId;
    private int memberSellerId;
    private String name;
    private int price;
    private Date entryDate;
    private int quantity;
    private String content;
}
