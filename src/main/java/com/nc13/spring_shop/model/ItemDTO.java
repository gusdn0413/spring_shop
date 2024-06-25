package com.nc13.spring_shop.model;

import lombok.Data;

import java.util.Date;

@Data
public class ItemDTO {

    private int id;
    private int price;
    private int quantity;
    private String name;
    private Date entryDate;
    private Date modifyDate;
    private int memberSellerId;
    private String nickname;
    private int phoneNumber;
    private int categoryId;
}
