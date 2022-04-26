package com.urban.sonartest.balance.port.in;

import com.urban.sonartest.balance.domain.dto.CommandDto;

import java.util.Map;

public interface BalanceDslParser {
    CommandDto parseCommand(String command);
}
