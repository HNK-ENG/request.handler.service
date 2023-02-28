package com.example.request.handler.service.service;


import com.example.request.handler.service.clientintegration.metadatamanagement.MetadataClient;
import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.metadata.CityDetails;
import com.example.request.handler.service.dto.metadata.DistrictDetails;
import com.example.request.handler.service.dto.metadata.ItemDetails;
import com.example.request.handler.service.serviceinterface.MetaDataServiceInterface;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaDataService implements MetaDataServiceInterface {
    @Autowired
    MetadataClient metadataClient;

    @Override
    public AdaptorResponse<List<ItemDetails>> getItems() throws BaseException {
        try{
            return metadataClient.getItems();
        }catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }

    }

    @Override
    public AdaptorResponse<List<DistrictDetails>> getDistrict() throws BaseException {
        try{
            return metadataClient.getDistrict();
        }catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }

    @Override
    public AdaptorResponse<List<CityDetails>> getCity(Long districtId) throws BaseException {
        try{
            return metadataClient.getCity(districtId);
        }catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }
}
