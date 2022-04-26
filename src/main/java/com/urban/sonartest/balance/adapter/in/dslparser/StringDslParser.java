package com.urban.sonartest.balance.adapter.in.dslparser;


import com.urban.sonartest.balance.domain.dto.CommandDto;
import com.urban.sonartest.balance.port.in.BalanceDslParser;
import org.springframework.stereotype.Service;

@Service
public class StringDslParser implements BalanceDslParser {
    @Override
    public CommandDto parseCommand(String command) {
        String[] parts = command.split(" ");
        CommandDto result = null;
        if(parts[0].equalsIgnoreCase("PRINT")){
            result = CommandDto.builder()
                    .operation(parts[0])
                    .param(parts[1])
                    .date(parts[2])
                    .build();
        }
        if(parts[0].equalsIgnoreCase("INCOME") || parts[0].equalsIgnoreCase("EXPENSE") ){
            result = CommandDto.builder()
                    .operation(parts[0])
                    .date(parts[1])
                    .description(parts[2])
                    .value(parts[3])
                    .build();
        }
        return result;
    }
}
