package com.example.request.handler.service.dto.register;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerRegRequest {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String identityNumber;
    private String district;
    private String city;
    private String street1;
    private String street2;
    private String houseNo;

}
