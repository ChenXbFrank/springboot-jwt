package com.cxb.jwt.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 昵称
     */
    private String name;
    /**
     * 密码
     */
    private String password;
}

