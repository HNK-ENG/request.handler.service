package com.example.request.handler.service.controller.userController;


import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.register.TokenResponse;
import com.example.request.handler.service.dto.register.UserLoginRequest;
import com.example.request.handler.service.service.JwtService;
import com.example.request.handler.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/authenticate")
public class UserAuthenticate {

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity<AdaptorResponse<TokenResponse>> generateToken(@RequestBody UserLoginRequest loginRequest, HttpServletRequest httpServletRequest)throws BaseException{
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        if(authentication.isAuthenticated()){
           AdaptorResponse<TokenResponse> response = jwtService.generateToken(loginRequest);
            return ResponseEntity.ok()
                    .body(response);
        }else {
            throw new UsernameNotFoundException("Invalid User");
        }

    }




}
