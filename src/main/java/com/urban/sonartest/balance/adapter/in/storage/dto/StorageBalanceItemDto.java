package com.urban.sonartest.balance.adapter.in.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageBalanceItemDto {
    private Integer id;
    private Integer day;
    private Integer month;
    private Integer year;
    private String type;
    private String value;
    private String description;
}
