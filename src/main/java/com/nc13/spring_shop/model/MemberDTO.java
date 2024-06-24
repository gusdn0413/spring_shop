package com.nc13.spring_shop.model;

import lombok.Data;

@Data
public class MemberDTO {
    private int id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private int role;
    private String address;
    private String phoneNumber;
    private Gender gender;
}
