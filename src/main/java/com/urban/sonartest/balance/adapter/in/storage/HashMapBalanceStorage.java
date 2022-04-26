package com.urban.sonartest.balance.adapter.in.storage;

import com.urban.sonartest.balance.adapter.in.storage.dto.StorageBalanceItemDto;
import com.urban.sonartest.balance.adapter.in.storage.translator.StorageBalanceTranslator;
import com.urban.sonartest.balance.domain.model.BalanceItem;
import com.urban.sonartest.balance.port.in.BalanceStorage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HashMapBalanceStorage implements BalanceStorage {
    private final HashMap<Integer, StorageBalanceItemDto> repository = new HashMap<>();

    @Override
    public Integer addBalanceItem(BalanceItem balanceItem) {
        BalanceItem newBalanceItem = balanceItem.toBuilder().id(getNextId()).build();
        repository.put(newBalanceItem.getId(), StorageBalanceTranslator.toStorageDto(newBalanceItem));
        return newBalanceItem.getId();
    }

    @Override
    public List<BalanceItem> getBalanceItemsForDay(Integer day, Integer month, Integer year) {
        return repository.values().stream()
                .filter(it -> it.getYear().equals(year) &&
                        it.getMonth().equals(month) &&
                        it.getDay().equals(day))
                .map(StorageBalanceTranslator::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<BalanceItem> getBalanceItemsForMonth(Integer month, Integer year) {
        return repository.values().stream()
                .filter(it -> it.getMonth().equals(month) && it.getYear().equals(year))
                .map(StorageBalanceTranslator::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<BalanceItem> getBalanceItemsForYear(Integer year) {
        return repository.values().stream()
                .filter(it -> it.getYear().equals(year))
                .map(StorageBalanceTranslator::toDomainModel)
                .collect(Collectors.toList());
    }

    private Integer getNextId() {
        if(repository.size() == 0){
            return 0;
        }
        return repository.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }
}
