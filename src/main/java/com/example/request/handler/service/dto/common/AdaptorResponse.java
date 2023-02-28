package com.example.request.handler.service.dto.common;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AdaptorResponse<T> {
    private T data;
    private Result result;
}
