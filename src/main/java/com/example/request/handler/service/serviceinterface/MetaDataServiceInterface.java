package com.example.request.handler.service.serviceinterface;

import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.metadata.CityDetails;
import com.example.request.handler.service.dto.metadata.DistrictDetails;
import com.example.request.handler.service.dto.metadata.ItemDetails;
import com.example.request.handler.service.util.exception.BaseException;

import java.util.List;

public interface MetaDataServiceInterface {
    AdaptorResponse<List<ItemDetails>> getItems() throws BaseException;

    AdaptorResponse<List<DistrictDetails>> getDistrict() throws BaseException;

    AdaptorResponse<List<CityDetails>> getCity(Long districtId) throws BaseException;
}
