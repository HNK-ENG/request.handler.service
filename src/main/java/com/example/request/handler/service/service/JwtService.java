package com.example.request.handler.service.service;


import com.example.request.handler.service.dto.common.AdaptorResponse;
import com.example.request.handler.service.dto.common.Result;
import com.example.request.handler.service.dto.register.TokenResponse;
import com.example.request.handler.service.dto.register.UserLoginRequest;
import com.example.request.handler.service.repository.User;
import com.example.request.handler.service.repository.UserRepository;
import com.example.request.handler.service.util.constants.Constants;
import com.example.request.handler.service.util.exception.BaseException;
import com.example.request.handler.service.util.responsehandler.SuccessHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {

    @Autowired
    UserRepository userRepository;

    private String secretKey = "2A462D4A614E645267556B586E3272357538782F413F4428472B4B6250655368";


    public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        private Claims extractAllClaims(String token) {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        public Boolean validateToken(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }

    public AdaptorResponse<TokenResponse> generateToken(UserLoginRequest loginRequest) throws BaseException {
        Map<String, Object>claims = new HashMap<>();
        List<User> userList = userRepository.findByName(loginRequest.getUserName());
        if(userList!=null && !userList.isEmpty()) {
            claims.put("roles", userList.get(0).getRole().split(","));
            claims.put("userId", userList.get(0).getId());
        }
        return createToken(claims, loginRequest.getUserName());
    }

    private AdaptorResponse<TokenResponse> createToken(Map<String, Object> claims, String userName) throws BaseException {
        String token =  Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        Result result = new Result();
        result.setResultCode(Constants.SUCCESS_CODE);
        result.setResultDescription(Constants.SUCCESS_DESCRIPTION);
        return AdaptorResponse.<TokenResponse>builder().data(tokenResponse).result(result).build();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
