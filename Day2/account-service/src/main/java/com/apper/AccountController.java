package com.apper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {
    //Instance Variable
    private final AccountService accountService;
    //Constructor
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        Account account = accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());
        response.setGeneratedId(account.getId());

    return response;
    }

    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());
        response.setAccountId(account.getId());

        return response;
    }

    @GetMapping("all")
    public List<GetAccountResponse> getAllAccounts() {
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = new GetAccountResponse();
            response.setBalance(account.getBalance());
            response.setFirstName(account.getFirstName());
            response.setLastName(account.getLastName());
            response.setUsername(account.getUsername());
            response.setRegistrationDate(account.getCreationDate());
            response.setAccountId(account.getId());

            responseList.add(response);
        }
        return responseList;
    }

    @DeleteMapping("{accountId}")
    public DeleteAccountResponse deleteAccount(@PathVariable String accountId) {
        Account account = accountService.delete(accountId);

        DeleteAccountResponse response = new DeleteAccountResponse();
        response.setMessage("Delete Successful");

        return response;
    }
}
