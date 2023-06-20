package com.gcash.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditApi {
    private final CreditService creditService;

    public CreditApi(CreditService creditService) {
        this.creditService = creditService;
    }

    // create an account
    @PostMapping
    public Account createNewAccount(@RequestBody CreateAccount createAccount) {
        return creditService.createAccount(createAccount.getInitialBalance());
    }

    //retrieve all accounts
    @GetMapping
    public List<Account> getAllAccount() {
        return creditService.getAllAccounts();
    }
}
