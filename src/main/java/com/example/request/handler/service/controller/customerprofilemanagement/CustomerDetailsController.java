package com.example.request.handler.service.controller.customerprofilemanagement;

import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.customer.CustomerDetails;
import com.example.request.handler.service.dto.register.CustomerRegRequest;
import com.example.request.handler.service.kafkaproducer.KafkaProducer;
import com.example.request.handler.service.serviceinterface.CustomerServiceInterface;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rhs/customer")
@CrossOrigin(origins = "*")
public class CustomerDetailsController {

    @Autowired
    CustomerServiceInterface customerServiceInterface;


    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("/{customerId}")
    ResponseEntity<AdaptorResponse<CustomerDetails>> getCustomer(@PathVariable String customerId, HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<CustomerDetails> adaptorResponse = customerServiceInterface.getCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);
    }

    @PostMapping("/save")
    ResponseEntity<AdaptorResponse<String>> saveCustomer(@RequestBody CustomerRegRequest customerRegRequest, HttpServletRequest httpServletRequest) throws BaseException{
        AdaptorResponse<String> adaptorResponse = kafkaProducer.createCustomer(customerRegRequest);
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);
    }









}
