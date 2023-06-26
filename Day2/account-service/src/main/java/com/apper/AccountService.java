package com.apper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService {

    private List<Account> accounts = new ArrayList<>();
    public Account create(String firstName, String lastName, String username, String clearPassword) {
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setBalance(1_000.0);

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);

        accounts.add(account);
        return account;
    }

//    public Account get() {
//
//    }
//
//    public Account update() {
//
//    }
//
//    public Account delete() {
//
//    }
}
