package com.example.request.handler.service.dto.salesorders.createOrder;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class OrderDetails {
    private Long id;
    private String customerId;
    private String item_name;
    private String quantity;
    private String totalCost;
    private String status;
    private String createdDatetime;
}
