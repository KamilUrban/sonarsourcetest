package com.urban.sonartest.balance.adapter.out.controller;

import com.urban.sonartest.balance.adapter.in.dslparser.StringDslParser;
import com.urban.sonartest.balance.adapter.out.web.controller.BalanceController;
import com.urban.sonartest.balance.port.out.BalanceModifyService;
import com.urban.sonartest.balance.port.out.BalanceQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BalanceController.class)
@Import(StringDslParser.class)
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private BalanceQueryService queryService;
    @MockBean
    private BalanceModifyService modifyService;


    @Test
    public void shouldCreateNewIncome() throws Exception {
        when(modifyService.addBalanceItem(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(0);
        this.mockMvc.perform(post("/balance").content("INCOME 2020/01/01 gift 500")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("created new item, id: 0")));
    }

    @Test
    public void shouldCreateNewExpense() throws Exception {
        when(modifyService.addBalanceItem(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(0);
        this.mockMvc.perform(post("/balance").content("EXPENSE 2020/01/01 coffe 5")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("created new item, id: 0")));
    }

    @Test
    public void shouldCalculateBalance() throws Exception {
        when(queryService.getBalanceForDay(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(5);
        this.mockMvc.perform(post("/balance").content("PRINT DAY 2020/01/01")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Balance calculated as: 5")));

    }


}
