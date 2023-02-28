package com.example.request.handler.service.clientintegration.metadatamanagement;


import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.metadata.CityDetails;
import com.example.request.handler.service.dto.metadata.DistrictDetails;
import com.example.request.handler.service.dto.metadata.ItemDetails;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class MetadataClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${vms.get.items.url}")
    private String getItemsUrl;

    @Value("${vms.get.district.url}")
    private String getDistrictUrl;

    @Value("${vms.get.city.url}")
    private String getCityUrl;

    public AdaptorResponse<List<ItemDetails>> getItems() throws BaseException{
        try{
            ParameterizedTypeReference<AdaptorResponse<List<ItemDetails>>> typeRef = new ParameterizedTypeReference<AdaptorResponse<List<ItemDetails>>>() {
            };
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", MediaType.APPLICATION_JSON.toString());

            return restTemplate.exchange(getItemsUrl, HttpMethod.GET, new HttpEntity<>(headers), typeRef).getBody();

        } catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }


    }

    public AdaptorResponse<List<DistrictDetails>> getDistrict()throws BaseException {
        try{
            ParameterizedTypeReference<AdaptorResponse<List<DistrictDetails>>> typeRef = new ParameterizedTypeReference<AdaptorResponse<List<DistrictDetails>>>() {
            };
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", MediaType.APPLICATION_JSON.toString());

            return restTemplate.exchange(getDistrictUrl, HttpMethod.GET, new HttpEntity<>(headers), typeRef).getBody();

        } catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }

    public AdaptorResponse<List<CityDetails>> getCity(Long districtId)throws BaseException {
        try{
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(getCityUrl).queryParam(Constants.DISTRICT_ID, districtId);
            ParameterizedTypeReference<AdaptorResponse<List<CityDetails>>> typeRef = new ParameterizedTypeReference<AdaptorResponse<List<CityDetails>>>() {
            };
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", MediaType.APPLICATION_JSON.toString());

            return restTemplate.exchange(uriComponentsBuilder.build().toString(), HttpMethod.GET, new HttpEntity<>(headers), typeRef).getBody();

        } catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }
}
