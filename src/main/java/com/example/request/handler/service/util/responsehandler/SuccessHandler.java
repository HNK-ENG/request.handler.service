package com.example.request.handler.service.util.responsehandler;


import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.common.Result;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SuccessHandler {

    public AdaptorResponse<String> responseHandler() throws BaseException{
        try{
            Result result = new Result();
            result.setResultCode(Constants.SUCCESS_CODE);
            result.setResultDescription(Constants.SUCCESS_DESCRIPTION);
            return AdaptorResponse.<String>builder().data(Constants.SUCCESS_DESCRIPTION).result(result).build();


        }catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }




}
