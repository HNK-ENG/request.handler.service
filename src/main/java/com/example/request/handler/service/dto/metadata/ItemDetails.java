package com.example.request.handler.service.dto.metadata;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemDetails {
    private Long id;
    private String itemName;
    private Long itemsPerCost;
}
