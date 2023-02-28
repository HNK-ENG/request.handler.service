package com.example.request.handler.service.dto.salesorders.createOrder;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsPage {
    private Long totalRecord;
    private String orderId;
    private String status;
    private String customerId;
    private String itemName;
    private String quantity;
    private String totalCost;
    private String createdDatetime;
}
