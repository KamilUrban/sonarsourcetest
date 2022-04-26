package com.urban.sonartest.balance.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommandDto {
    private String operation;
    private String param;
    private String date;
    private String description;
    private String value;
}
