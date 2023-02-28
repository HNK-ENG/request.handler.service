package com.example.request.handler.service.dto.common;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String resultCode;
    private String resultDescription;
}
