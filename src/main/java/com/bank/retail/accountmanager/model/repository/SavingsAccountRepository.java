package com.bank.retail.accountmanager.model.repository;

import com.bank.retail.accountmanager.model.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
    List<SavingsAccount> findByUser(String username);
}
