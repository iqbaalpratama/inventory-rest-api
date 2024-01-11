package com.iqbaal.inventory.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse <T> {
    private String status;
    private int httpStatus;
    private String message;
    private T data;

}