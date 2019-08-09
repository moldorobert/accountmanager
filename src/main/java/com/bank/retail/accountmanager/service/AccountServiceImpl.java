package com.bank.retail.accountmanager.service;

import com.bank.retail.accountmanager.model.dto.Account;
import com.bank.retail.accountmanager.model.entity.SavingsAccount;
import com.bank.retail.accountmanager.model.repository.SavingsAccountRepository;
import com.bank.retail.accountmanager.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Value("${app.allow-non-business-hours}")
    private boolean allowNonBusinessHours;

    @Override
    public boolean addAccount(Account account) {
        checkAccountCanBeAdded();

        SavingsAccount acc = toSavingsAccount(account);
        savingsAccountRepository.save(acc);

        return true;
    }

    private void checkAccountCanBeAdded() {
        int hour = LocalDateTime.now().getHour();
        if (!allowNonBusinessHours && (hour < 8 || hour > 17)) {
            throw new IllegalStateException("No account can be added outside of business hours");
        }

        List<Account> existingAccounts = getAccounts();
        if (!CollectionUtils.isEmpty(existingAccounts)) {
            throw new IllegalStateException("Account was not added because you can have only one savings account!");
        }
    }

    @Override
    public List<Account> getAccounts() {
        return savingsAccountRepository.findByUser(AuthUtil.getLoggedInUser())
                .stream()
                .map(this::toAccount)
                .collect(Collectors.toList());
    }

    private SavingsAccount toSavingsAccount(Account account) {
        SavingsAccount acc = new SavingsAccount();
        acc.setUser(AuthUtil.getLoggedInUser());
        acc.setAmount(account.getAmount());
        acc.setFundsAccount(account.getFundsAccount());
        acc.setPeriodOfTime(Integer.parseInt(account.getPeriodOfTime()));
        return acc;
    }

    private Account toAccount(SavingsAccount account) {
        Account acc = new Account();
        acc.setAmount(account.getAmount());
        acc.setFundsAccount(account.getFundsAccount());
        acc.setPeriodOfTime(String.valueOf(account.getPeriodOfTime()));
        return acc;
    }
}
