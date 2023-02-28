package com.example.request.handler.service.clientintegration.customerManagement;


import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.customer.CustomerDetails;
import com.example.request.handler.service.dto.customer.ViewCustomerOrderHistoryResponse;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerManagementClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${vms.view.customer.url}")
    private String viewCustomerUrl;


    public AdaptorResponse<CustomerDetails> getCustomer(String customerId) throws BaseException {
        try{
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(viewCustomerUrl);

            ParameterizedTypeReference<AdaptorResponse<CustomerDetails>> typeRef = new ParameterizedTypeReference<AdaptorResponse<CustomerDetails>>() {
            };
            Map<String, String> pathVariables = new HashMap<>();
            pathVariables.put(Constants.CUSTOMER_ID, customerId);


            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", MediaType.APPLICATION_JSON.toString());

            return restTemplate.exchange(uriComponentsBuilder.buildAndExpand(pathVariables).toString(), HttpMethod.GET, new HttpEntity<>(headers), typeRef).getBody();

        } catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }
}
