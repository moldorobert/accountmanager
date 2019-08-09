package com.bank.retail.accountmanager.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Table
@Entity
public class SavingsAccount {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "FUNDS_ACCOUNT")
    private String fundsAccount;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "PERIOD_OF_TIME")
    private Integer periodOfTime;

    @Column(name = "USER")
    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundsAccount() {
        return fundsAccount;
    }

    public void setFundsAccount(String fundsAccount) {
        this.fundsAccount = fundsAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPeriodOfTime() {
        return periodOfTime;
    }

    public void setPeriodOfTime(Integer periodOfTime) {
        this.periodOfTime = periodOfTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
