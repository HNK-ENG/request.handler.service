package com.example.request.handler.service.dto.register;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginRequest {
    private String userName;
    private String password;
}
