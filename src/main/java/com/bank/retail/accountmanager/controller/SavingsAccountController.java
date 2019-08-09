package com.bank.retail.accountmanager.controller;

import com.bank.retail.accountmanager.model.dto.Account;
import com.bank.retail.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SavingsAccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping({"/", "/error"})
    public String loadRoot(Account account) {
        return "index";
    }

    @PostMapping("/account")
    public String addAccount(@Valid Account account, RedirectAttributes redirectAttributes) {
        accountService.addAccount(account);
        redirectAttributes.addAttribute("msg", "Account was added!");
        redirectAttributes.addAttribute("msgType", "alert-success");
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ModelAndView handleValidationExceptions(
            BindException ex) {
        StringBuilder err = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            err.append(fieldName).append(":").append(errorMessage).append("; ");
        });
        return getMAV(err.toString());
    }

    private ModelAndView getMAV(String err) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errMsg", err);
        mav.addObject("account", new Account());
        mav.setViewName("index");
        return mav;
    }

    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ModelAndView handleIAG(
            RuntimeException ex) {
        return getMAV(ex.getMessage());
    }
}
