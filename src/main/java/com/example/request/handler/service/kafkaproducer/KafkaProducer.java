package com.example.request.handler.service.kafkaproducer;

import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.common.Result;
import com.example.request.handler.service.dto.customer.CustomerDetails;
import com.example.request.handler.service.dto.customer.ViewCustomerOrderHistoryResponse;
import com.example.request.handler.service.dto.register.CustomerRegRequest;
import com.example.request.handler.service.dto.salesorders.createOrder.SalesOrderCreationRequest;
import com.example.request.handler.service.dto.salesorders.updateOrder.KafkaOrderCommon;
import com.example.request.handler.service.kafkamappers.KafkaObjectMapper;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import com.example.request.handler.service.util.responsehandler.SuccessHandler;
import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class KafkaProducer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SuccessHandler successHandler;

    @Value("${order.topic}")
    private String orderTopic;

    @Value("${customer.topic}")
    private String customerTopic;

    @Autowired
    KafkaObjectMapper kafkaObjectMapper;



    public AdaptorResponse<String> cancelOrder(String orderId) throws BaseException {
        try{
            KafkaOrderCommon kafkaOrderCommon = kafkaObjectMapper.orderCommon(orderId, Constants.CANCEL, null);
            Gson gson = new Gson();
            kafkaTemplate.send(orderTopic, gson.toJson(kafkaOrderCommon));
            return successHandler.responseHandler();

        } catch (BaseException ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }

    }

    public AdaptorResponse<String> dispatchedOrder(String orderId) throws BaseException {
        try{
            KafkaOrderCommon kafkaOrderCommon = kafkaObjectMapper.orderCommon(orderId, Constants.DISPATCH, null);
            Gson gson = new Gson();
            kafkaTemplate.send(orderTopic, gson.toJson(kafkaOrderCommon));
            return successHandler.responseHandler();

        } catch (BaseException ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }

    }


    public AdaptorResponse<String> confirmOrder(String orderId) throws BaseException {
        try{
            KafkaOrderCommon kafkaOrderCommon = kafkaObjectMapper.orderCommon(orderId, Constants.SUCCESS, null);
            Gson gson = new Gson();
            kafkaTemplate.send(orderTopic, gson.toJson(kafkaOrderCommon));
            return successHandler.responseHandler();

        } catch (BaseException ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }

    }

    public AdaptorResponse<String> createOrder(SalesOrderCreationRequest salesOrderCreationRequest) throws BaseException {
        try{
            KafkaOrderCommon kafkaOrderCommon = kafkaObjectMapper.orderCommon(null, Constants.CREATE_ORDER, salesOrderCreationRequest);
            Gson gson = new Gson();
            kafkaTemplate.send(orderTopic, gson.toJson(kafkaOrderCommon));
            return successHandler.responseHandler();

        } catch (BaseException ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }

    }


    public AdaptorResponse<String> createCustomer(CustomerRegRequest customerRegRequest) throws BaseException {
        try{
            customerRegRequest.setPassword(passwordEncoder.encode(customerRegRequest.getPassword()));
            Gson gson = new Gson();
            kafkaTemplate.send(customerTopic, gson.toJson(customerRegRequest));
            return successHandler.responseHandler();

        } catch (BaseException ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }
}
