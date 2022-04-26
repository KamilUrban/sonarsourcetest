package com.urban.sonartest.balance.adapter.in.storage.exception;

public class BalanceItemExistsException extends RuntimeException {
    public BalanceItemExistsException(Integer id) {
        super(String.format("Balance item id: [%s] already exists in storage", id));
    }
}
