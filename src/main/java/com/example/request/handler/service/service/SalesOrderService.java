package com.example.request.handler.service.service;



import com.example.request.handler.service.clientintegration.salesordermanagement.SalesOrderManagementClient;
import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.customer.ViewCustomerOrderHistoryResponse;
import com.example.request.handler.service.serviceinterface.SalesOrderInterface;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderService implements SalesOrderInterface {

    @Autowired
    SalesOrderManagementClient salesOrderManagementClient;
    @Override
    public AdaptorResponse<ViewCustomerOrderHistoryResponse> viewHistory(int pageNumber, int itemsPerPage, String customerId, String status) throws BaseException {
        try {
            return salesOrderManagementClient.viewHistory(pageNumber, itemsPerPage, customerId, status);
        } catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }

    }
}
