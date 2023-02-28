package com.example.request.handler.service.kafkamappers;


import com.example.request.handler.service.dto.salesorders.createOrder.OrderData;
import com.example.request.handler.service.dto.salesorders.createOrder.SalesOrderCreationRequest;
import com.example.request.handler.service.dto.salesorders.updateOrder.KafkaOrderCommon;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class KafkaObjectMapper {

    public KafkaOrderCommon orderCommon (String orderId, String action, SalesOrderCreationRequest salesOrderCreationRequest) throws BaseException{
        KafkaOrderCommon kafkaOrderCommon = new KafkaOrderCommon();
        kafkaOrderCommon.setOrderId(orderId);
        kafkaOrderCommon.setAction(action);
        if(salesOrderCreationRequest!=null){
            kafkaOrderCommon.setOrderData(salesOrderCreationRequest);
        }
        return kafkaOrderCommon;
    }



}
