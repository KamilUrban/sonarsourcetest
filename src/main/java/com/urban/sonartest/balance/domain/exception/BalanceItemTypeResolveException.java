package com.urban.sonartest.balance.domain.exception;

public class BalanceItemTypeResolveException extends RuntimeException {
    public BalanceItemTypeResolveException(String type) {
        super("Could not resolve balance type: " + type);
    }
}
