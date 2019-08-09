package com.bank.retail.accountmanager.model.dto;

import javax.validation.constraints.*;

public class Account {
    @NotEmpty
    private String fundsAccount;

    @Positive
    @NotNull
    private Double amount;

    @Pattern(regexp = "[136]")
    private String periodOfTime;

    public String getPeriodOfTime() {
        return periodOfTime;
    }

    public void setPeriodOfTime(String periodOfTime) {
        this.periodOfTime = periodOfTime;
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
}
