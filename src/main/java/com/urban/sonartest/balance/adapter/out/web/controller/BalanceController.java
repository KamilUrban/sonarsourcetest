package com.urban.sonartest.balance.adapter.out.web.controller;

import com.urban.sonartest.balance.adapter.out.web.controller.dto.BalanceControllerResponse;
import com.urban.sonartest.balance.domain.dto.CommandDto;
import com.urban.sonartest.balance.port.in.BalanceDslParser;
import com.urban.sonartest.balance.port.out.BalanceModifyService;
import com.urban.sonartest.balance.port.out.BalanceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceModifyService balanceModifyService;
    private final BalanceQueryService balanceQueryService;
    private final BalanceDslParser balanceDslParser;

    @PostMapping("/balance")
    public ResponseEntity<BalanceControllerResponse> executeCommand(@RequestBody String command) {
        BalanceControllerResponse response = BalanceControllerResponse.builder().build();
        CommandDto commandDto = balanceDslParser.parseCommand(command);

        if (commandDto.getOperation().equalsIgnoreCase("INCOME")
                || commandDto.getOperation().equalsIgnoreCase("EXPENSE")) {
            Integer id = balanceModifyService.addBalanceItem(commandDto.getOperation(), commandDto.getDescription(), commandDto.getDate(), commandDto.getValue());
            response.setResponse("created new item, id: " + id);
        }
        if (commandDto.getOperation().equalsIgnoreCase("PRINT")) {
            if(commandDto.getParam().equalsIgnoreCase("DAY")){
                Integer result = balanceQueryService.getBalanceForDay(getDay(commandDto.getDate()), getMonth(commandDto.getDate()), getYear(commandDto.getDate()));
                response.setResponse("Balance calculated as: " + result);
            }
            if(commandDto.getParam().equalsIgnoreCase("MONTH")){
                Integer result = balanceQueryService.getBalanceForMonth(getMonth(commandDto.getDate()), getYear(commandDto.getDate()));
                response.setResponse("Balance calculated as: " + result);
            }
            if(commandDto.getParam().equalsIgnoreCase("YEAR")){
                Integer result = balanceQueryService.getBalanceForYear(getYear(commandDto.getDate()));
                response.setResponse("Balance calculated as: " + result);
            }
        }

        return ResponseEntity.ok(response);
    }

    private Integer getYear(String date) {
        String[] parts = date.split("/");
        return Integer.parseInt(parts[0]);
    }

    private Integer getMonth(String date) {
        String[] parts = date.split("/");
        return Integer.parseInt(parts[1]);
    }

    private Integer getDay(String date) {
        String[] parts = date.split("/");
        return Integer.parseInt(parts[2]);
    }
}
