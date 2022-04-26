package com.urban.sonartest.balance.domain.service;

import com.urban.sonartest.balance.domain.model.BalanceItem;
import com.urban.sonartest.balance.domain.model.BalanceItemType;
import com.urban.sonartest.balance.port.in.BalanceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceQueryService implements com.urban.sonartest.balance.port.out.BalanceQueryService {
    private final BalanceStorage balanceStorage;

    public Integer getBalanceForDay(Integer day, Integer month, Integer year){
        return calculateBalance(balanceStorage.getBalanceItemsForDay(day, month, year));
    }

    public Integer getBalanceForMonth(Integer month, Integer year){
        return calculateBalance(balanceStorage.getBalanceItemsForMonth(month, year));
    }
    public Integer getBalanceForYear(Integer year){
        return calculateBalance(balanceStorage.getBalanceItemsForYear(year));
    }

    private Integer calculateBalance(List<BalanceItem> balanceItemsForDay) {
        return balanceItemsForDay.stream().reduce(
                0,
                (sum, balanceItem) -> {
                    if(balanceItem.getType().equals(BalanceItemType.INCOME))
                    {
                        return sum + Integer.parseInt(balanceItem.getValue());
                    }
                    return sum - Integer.parseInt(balanceItem.getValue());
                },
                Integer::sum
        );
    }
}
