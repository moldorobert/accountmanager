package com.bank.retail.accountmanager.service;

import com.bank.retail.accountmanager.model.dto.Account;

import java.util.List;

public interface AccountService {
    boolean addAccount(Account account);
    List<Account> getAccounts();
}
