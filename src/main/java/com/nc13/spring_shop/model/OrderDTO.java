package com.nc13.spring_shop.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
    private int id;
    private int itemId;
    private int memberCustomerId;
    private int memberSellerId;
    private int price;
    private int quantity;
    private Date entryDate;
    private String name;
    private String email;
    private String address;
    private String content;
}
