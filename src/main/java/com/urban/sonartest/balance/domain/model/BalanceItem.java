package com.urban.sonartest.balance.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BalanceItem {
    private Integer id;
    private Integer day;
    private Integer month;
    private Integer year;
    private BalanceItemType type;
    private String value;
    private String description;
}
