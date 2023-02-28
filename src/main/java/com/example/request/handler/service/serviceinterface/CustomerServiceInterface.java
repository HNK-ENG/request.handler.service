package com.example.request.handler.service.serviceinterface;

import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.customer.CustomerDetails;
import com.example.request.handler.service.util.exception.BaseException;

public interface CustomerServiceInterface {
    AdaptorResponse<CustomerDetails> getCustomer(String customerId) throws BaseException;
}
