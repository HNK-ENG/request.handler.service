package com.example.request.handler.service.controller.metadatamanagement;


import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.metadata.CityDetails;
import com.example.request.handler.service.dto.metadata.DistrictDetails;
import com.example.request.handler.service.dto.metadata.ItemDetails;
import com.example.request.handler.service.dto.register.TokenResponse;
import com.example.request.handler.service.serviceinterface.MetaDataServiceInterface;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rhs/metaData")
@CrossOrigin(origins = "*")
public class MetaDataController {

    @Autowired
    MetaDataServiceInterface metaDataServiceInterface;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "NewTopic";

    @GetMapping("/itemsDropDown")
    ResponseEntity<AdaptorResponse<List<ItemDetails>>> getItems(HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<List<ItemDetails>> adaptorResponse = metaDataServiceInterface.getItems();
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);
    }

    @GetMapping("/district-drop-down")
    ResponseEntity<AdaptorResponse<List<DistrictDetails>>> getDistrict(HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<List<DistrictDetails>> adaptorResponse = metaDataServiceInterface.getDistrict();
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);

    }

    @GetMapping("/city-drop-down")
    ResponseEntity<AdaptorResponse<List<CityDetails>>> getCity(@RequestParam(name = Constants.DISTRICT_ID) Long districtId, HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<List<CityDetails>> adaptorResponse = metaDataServiceInterface.getCity(districtId);
        return ResponseEntity.status(HttpStatus.OK).body(adaptorResponse);
    }







}
