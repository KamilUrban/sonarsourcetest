package com.urban.sonartest.balance.port.out;

public interface BalanceModifyService {
    Integer addBalanceItem(String type, String description, String date, String value);
}
