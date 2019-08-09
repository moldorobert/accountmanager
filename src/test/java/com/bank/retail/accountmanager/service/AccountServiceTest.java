package com.bank.retail.accountmanager.service;

import com.bank.retail.accountmanager.model.dto.Account;
import com.bank.retail.accountmanager.model.entity.SavingsAccount;
import com.bank.retail.accountmanager.model.repository.SavingsAccountRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {
        "app.allow-non-business-hours=true",
})
public class AccountServiceTest {

    @TestConfiguration
    static class AccountServiceTestContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountServiceImpl();
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private SavingsAccountRepository savingsAccountRepository;


    @Test
    @WithMockUser("test")
    public void testAddAccountWhenNoOtherAccountsExist() {
        when(savingsAccountRepository.findByUser(anyString()))
                .thenReturn(Collections.emptyList());
        Assert.assertTrue(accountService.addAccount(buildMockAccount()));
    }

    @Test(expected = IllegalStateException.class)
    @WithMockUser("test")
    public void testAddAccountWhenOtherAccountsExist() {
        when(savingsAccountRepository.findByUser(anyString()))
                .thenReturn(Collections.singletonList(mock(SavingsAccount.class)));
        Assert.assertTrue(accountService.addAccount(buildMockAccount()));
    }

    private Account buildMockAccount() {
        Account acc = new Account();
        acc.setAmount(100d);
        acc.setFundsAccount("ABC");
        acc.setPeriodOfTime("1");
        return acc;
    }

}
