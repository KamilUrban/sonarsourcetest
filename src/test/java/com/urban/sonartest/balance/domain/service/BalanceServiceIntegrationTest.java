package com.urban.sonartest.balance.domain.service;


import com.urban.sonartest.balance.adapter.in.storage.HashMapBalanceStorage;
import com.urban.sonartest.balance.port.in.BalanceStorage;
import com.urban.sonartest.balance.port.out.BalanceModifyService;
import com.urban.sonartest.balance.port.out.BalanceQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BalanceServiceIntegrationTest {

    @TestConfiguration
    static class BalanceServiceIntegrationTestContextConfig {

        @Bean
        public BalanceStorage balanceStorage() {
            return new HashMapBalanceStorage();
        }

        @Bean
        public BalanceModifyService modifyService() {
            return new com.urban.sonartest.balance.domain.service.BalanceModifyService(balanceStorage());
        }


        @Bean
        public BalanceQueryService queryService() {
            return new com.urban.sonartest.balance.domain.service.BalanceQueryService(balanceStorage());
        }
    }

    @Autowired
    private BalanceQueryService queryService;

    @Autowired
    private BalanceModifyService modifyService;

    @Test
    public void shouldAddIncome() {
        modifyService.addBalanceItem("INCOME", "gift", "2020/01/01", "500");
        Integer balanceForDay = queryService.getBalanceForDay(1, 1, 2020);
        Assert.isTrue(balanceForDay.equals(500), "balance not matching: " + balanceForDay);
    }


    @Test
    public void shouldCalculateBalanceProperly() {
        modifyService.addBalanceItem("INCOME", "gift", "2020/01/01", "500");
        modifyService.addBalanceItem("EXPENSE", "coffe", "2020/01/01", "5");
        Integer balanceForDay = queryService.getBalanceForDay(1, 1, 2020);
        Assert.isTrue(balanceForDay.equals(495), "balance not matching: " + balanceForDay);
    }


    @Test
    public void shouldCalculateBalanceWithMultipleIncomesProperly() {
        modifyService.addBalanceItem("INCOME", "gift", "2020/01/01", "500");
        modifyService.addBalanceItem("INCOME", "stock-dividend", "2020/01/01", "500");
        modifyService.addBalanceItem("INCOME", "gift", "2020/01/02", "500");
        modifyService.addBalanceItem("EXPENSE", "coffee", "2020/01/01", "5");
        modifyService.addBalanceItem("EXPENSE", "coffee", "2020/01/02", "5");
        Integer balanceForDay1 = queryService.getBalanceForDay(1,1, 2020);
        Integer balanceForDay2 = queryService.getBalanceForDay(2, 1, 2020);

        Assert.isTrue(balanceForDay1.equals(995), "balance not matching: " + balanceForDay1);
        Assert.isTrue(balanceForDay2.equals(495), "balance not matching: " + balanceForDay2);
    }


}
