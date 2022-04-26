package com.urban.sonartest.balance.port.out;

public interface BalanceQueryService {
    Integer getBalanceForDay(Integer day, Integer month, Integer year);

    Integer getBalanceForMonth(Integer month, Integer year);

    Integer getBalanceForYear(Integer year);

}
