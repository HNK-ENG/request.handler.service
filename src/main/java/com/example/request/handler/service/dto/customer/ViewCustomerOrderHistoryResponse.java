package com.example.request.handler.service.dto.customer;


import com.example.request.handler.service.dto.salesorders.createOrder.OrderDetails;
import com.example.request.handler.service.dto.salesorders.createOrder.OrderDetailsPage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ViewCustomerOrderHistoryResponse {
    public int pageNumber;
    public int itemsPerPage;
    public int totalRecords;
    public List<OrderDetailsPage> data;
}
