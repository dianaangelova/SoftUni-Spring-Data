package com.example._05lab_springdataintro.services;

import com.example._05lab_springdataintro.exceptions.EntityMessageException;
import com.example._05lab_springdataintro.models.Account;
import com.example._05lab_springdataintro.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id)  {

        Optional<Account> account = this.accountRepository.findById(id);

        if (account.isEmpty()) {
            throw new EntityMessageException("Not valid account id");
        }

        BigDecimal deposit = account.get().getBalance();

        if (deposit.compareTo(money) < 0) {
            throw new RuntimeException("Not enough money into account to withdraw");
        } else {
            account.get().setBalance(deposit.subtract(money));
        }

        this.accountRepository.save(account.get());

    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {

      Account account =   this.accountRepository.findById(id).orElseThrow(()-> new RuntimeException("No such account"));

        BigDecimal currentBalance = account.getBalance();

        account.setBalance(currentBalance.add(money));

        this.accountRepository.save(account);

    }
}
