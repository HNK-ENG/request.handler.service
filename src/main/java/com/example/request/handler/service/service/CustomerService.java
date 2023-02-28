package com.example.request.handler.service.service;


import com.example.request.handler.service.clientintegration.customerManagement.CustomerManagementClient;
import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.customer.CustomerDetails;
import com.example.request.handler.service.serviceinterface.CustomerServiceInterface;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceInterface {

    @Autowired
    CustomerManagementClient customerManagementClient;

    @Override
    public AdaptorResponse<CustomerDetails> getCustomer(String customerId) throws BaseException {
        try{
            return customerManagementClient.getCustomer(customerId);
        }catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }
}
