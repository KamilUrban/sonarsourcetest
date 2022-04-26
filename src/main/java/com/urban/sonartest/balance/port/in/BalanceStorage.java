package com.urban.sonartest.balance.port.in;

import com.urban.sonartest.balance.domain.model.BalanceItem;

import java.util.List;

public interface BalanceStorage {

    Integer addBalanceItem(BalanceItem balanceItem);
    List<BalanceItem> getBalanceItemsForDay(Integer day, Integer month, Integer year);
    List<BalanceItem> getBalanceItemsForMonth(Integer month, Integer year);
    List<BalanceItem> getBalanceItemsForYear(Integer year);
}
