package com.example.request.handler.service.serviceinterface;


import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.customer.ViewCustomerOrderHistoryResponse;
import com.example.request.handler.service.dto.salesorders.createOrder.SalesOrderCreationRequest;
import com.example.request.handler.service.dto.salesorders.createOrder.SalesOrderCreationResponse;
import com.example.request.handler.service.util.exception.BaseException;

public interface SalesOrderInterface {
    AdaptorResponse<ViewCustomerOrderHistoryResponse> viewHistory(int pageNumber, int itemsPerPage, String customerId, String status) throws BaseException;
}
