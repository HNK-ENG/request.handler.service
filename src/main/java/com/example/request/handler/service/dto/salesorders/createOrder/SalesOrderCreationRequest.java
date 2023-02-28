package com.example.request.handler.service.dto.salesorders.createOrder;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesOrderCreationRequest {
    private String customerId;
    private String itemName;
    private Long quantity;
    private String totalCost;
}
