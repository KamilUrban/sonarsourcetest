package com.urban.sonartest.balance.adapter.in.storage.translator;

import com.urban.sonartest.balance.adapter.in.storage.dto.StorageBalanceItemDto;
import com.urban.sonartest.balance.domain.model.BalanceItem;
import com.urban.sonartest.balance.domain.model.BalanceItemType;
import org.springframework.stereotype.Service;

@Service
public class StorageBalanceTranslator {

    public static StorageBalanceItemDto toStorageDto(BalanceItem balance){
        return StorageBalanceItemDto.builder()
                .id(balance.getId())
                .type(balance.getType().toString())
                .day(balance.getDay())
                .month(balance.getMonth())
                .year(balance.getYear())
                .value(balance.getValue())
                .description(balance.getDescription())
                .build();
    }

    public static BalanceItem toDomainModel(StorageBalanceItemDto balance){
        return BalanceItem.builder()
                .id(balance.getId())
                .type(getType(balance))
                .year(balance.getYear())
                .month(balance.getMonth())
                .day(balance.getDay())
                .value(balance.getValue())
                .description(balance.getDescription())
                .build();
    }

    private static BalanceItemType getType(StorageBalanceItemDto balance) {
        return BalanceItemType.valueOf(balance.getType().toUpperCase());
    }
}
