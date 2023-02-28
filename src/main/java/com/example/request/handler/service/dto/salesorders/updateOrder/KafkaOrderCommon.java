package com.example.request.handler.service.dto.salesorders.updateOrder;


import com.example.request.handler.service.dto.salesorders.createOrder.OrderData;
import com.example.request.handler.service.dto.salesorders.createOrder.SalesOrderCreationRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KafkaOrderCommon {
    private String action;
    private String orderId;
    private SalesOrderCreationRequest orderData;

}
