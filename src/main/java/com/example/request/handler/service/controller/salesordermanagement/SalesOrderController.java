package com.example.request.handler.service.controller.salesordermanagement;


import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.customer.ViewCustomerOrderHistoryResponse;
import com.example.request.handler.service.dto.salesorders.createOrder.SalesOrderCreationRequest;
import com.example.request.handler.service.kafkaproducer.KafkaProducer;
import com.example.request.handler.service.serviceinterface.SalesOrderInterface;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rhs/salesOrder")
public class SalesOrderController {


    @Autowired
    SalesOrderInterface salesOrderInterface;

    @Autowired
    KafkaProducer kafkaProducer;


    @GetMapping("/orderHistory")
    @CrossOrigin(origins = "*", maxAge = 3600)
    ResponseEntity<AdaptorResponse<ViewCustomerOrderHistoryResponse>> viewHistory(@RequestParam(name = Constants.PAGE_NUMBER) int pageNumber, @RequestParam(name = Constants.ITEMS_PER_PAGE) int itemsPerPage, @RequestParam(name = Constants.CUSTOMER_ID, required = false) String customerId,@RequestParam(Constants.STATUS) String status, HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<ViewCustomerOrderHistoryResponse> adaptorResponse = salesOrderInterface.viewHistory(pageNumber, itemsPerPage, customerId, status);
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);
    }


    @GetMapping("/cancel/{orderId}")
    ResponseEntity<AdaptorResponse<String>> cancelOrder(@PathVariable(name = Constants.ORDER_ID) String orderId, HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<String> adaptorResponse = kafkaProducer.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);
    }

    @GetMapping("/dispatched/{orderId}")
    ResponseEntity<AdaptorResponse<String>> dispatchedOrder(@PathVariable(name = Constants.ORDER_ID) String orderId, HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<String> adaptorResponse = kafkaProducer.dispatchedOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);
    }

    @GetMapping("/confirm/{orderId}")
    ResponseEntity<AdaptorResponse<String>> confirmOrder(@PathVariable(name = Constants.ORDER_ID) String orderId, HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<String> adaptorResponse = kafkaProducer.confirmOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);
    }

    @PostMapping("/createOrder")
    ResponseEntity<AdaptorResponse<String>> confirmOrder(@RequestBody SalesOrderCreationRequest salesOrderCreationRequest, HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<String> adaptorResponse = kafkaProducer.createOrder(salesOrderCreationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);
    }


}
