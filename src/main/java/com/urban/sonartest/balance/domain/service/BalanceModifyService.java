package com.urban.sonartest.balance.domain.service;

import com.urban.sonartest.balance.domain.exception.BalanceItemTypeResolveException;
import com.urban.sonartest.balance.domain.model.BalanceItem;
import com.urban.sonartest.balance.domain.model.BalanceItemType;
import com.urban.sonartest.balance.port.in.BalanceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceModifyService implements com.urban.sonartest.balance.port.out.BalanceModifyService {

    private final BalanceStorage balanceStorage;


    public Integer addBalanceItem(String type, String description, String date, String value){
        log.info("CREATING NEW BALANCE ITEM");
        String[] dateParts = date.split("/");
        BalanceItem newItem = BalanceItem.builder()
                .type(resolveType(type))
                .description(description)
                .value(value)
                .year(Integer.parseInt(dateParts[0]))
                .month(Integer.parseInt(dateParts[1]))
                .day(Integer.parseInt(dateParts[2]))
                .build();

        log.info("ADDING BALANCE ITEM TO STORAGE");
        return balanceStorage.addBalanceItem(newItem);
    }

    private BalanceItemType resolveType(String type) {
        for (BalanceItemType value : BalanceItemType.values()) {
            if(value.toString().equals(type.toUpperCase())){
                return value;
            }
        }
        throw new BalanceItemTypeResolveException(type);
    }


}
